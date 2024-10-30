package com.hhplus.concert.application.lock;

import com.hhplus.concert.application.facade.ReservationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAspect {

    private final RedissonClient redissonClient;
    private final ReservationFacade reservationFacade;

    private final ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(distributedLock) && args(concertDateId, userId, concertDateSeatId)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock, Long concertDateId, Long userId, Long concertDateSeatId) throws Throwable {
        // SpEL 파싱을 위한 컨텍스트 생성
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("concertDateSeatId", concertDateSeatId);  // SpEL 바인딩

        // SpEL 표현식 평가 (key를 파싱)
        String lockKey = parser.parseExpression(distributedLock.key()).getValue(context, String.class);
        log.info("Try to acquire lock for key: {}", lockKey);
        RLock lock = redissonClient.getLock(lockKey);

        // 락을 획득할 때까지 기다리는 시간과 유지 시간 설정
        boolean isLocked = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), TimeUnit.SECONDS);

        if (!isLocked) {
            throw new IllegalStateException("Failed to acquire lock for key: " + lockKey);
        }

        try {
            return reservationFacade.reservation(concertDateId, userId, concertDateSeatId); // 메서드 실행
        } finally {
            lock.unlock(); // 락 해제
        }
    }
}
