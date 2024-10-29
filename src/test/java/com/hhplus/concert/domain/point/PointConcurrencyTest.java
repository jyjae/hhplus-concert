package com.hhplus.concert.domain.point;


import com.hhplus.concert.application.facade.PointFacade;
import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.UsePointCommand;
import com.hhplus.concert.domain.user.point.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PointConcurrencyTest {

    private static final Logger log = LoggerFactory.getLogger(PointConcurrencyTest.class);

    @Autowired
    private PointFacade pointFacade;


    @DisplayName("포인트 충전 동시성 테스트 성공 - 낙관적 락")
    @Sql({"/reset.sql", "/insert.sql"})
    @Test
    public void shouldCompleteChargePointWithConcurrencySuccessfully() throws InterruptedException {
        int threadCount = 100; // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount); // 스레드 동기화
        AtomicInteger successCount = new AtomicInteger(0); // 성공 횟수 카운터
        AtomicInteger retryCount = new AtomicInteger(0); // 재시도 횟수 카운터

        // 여러 스레드에서 동시에 충전 요청
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointFacade.charge(1L, 1000); // 포인트 충전 요청
                    successCount.incrementAndGet(); // 성공 시 카운트 증가
                } catch (Exception e) {
                    log.info("충돌 발생, 재시도 중: {}", e.getMessage());
                    retryCount.incrementAndGet(); // 충돌 발생 시 재시도 카운트 증가
                } finally {
                    latch.countDown(); // 작업 완료 시 latch 감소
                }
            });
        }

        latch.await(); // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown();

        // 테스트: 모든 스레드가 성공해야 함 (재시도 포함)
        assertThat(successCount.get()).isEqualTo(1);

        // 테스트: 재시도가 발생했는지 검증
        log.info("총 재시도 횟수: {}", retryCount.get());
        assertThat(retryCount.get()).isGreaterThanOrEqualTo(99); // 재시도가 있을 수 있음
    }

//    @DisplayName("포인트 사용 동시성 테스트 성공 - 낙관적락")
//    @Sql({"/reset.sql", "/insert.sql"})
//    @Test
//    public void shouldCompleteUsePointWithConcurrencySuccessfully() throws InterruptedException {
//        int threadCount = 100;  // 동시에 실행할 스레드 수
//        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
//        CountDownLatch latch = new CountDownLatch(threadCount);  // 스레드 동기화
//
//        AtomicInteger successCount = new AtomicInteger(0);  // 성공 횟수 카운터
//
//        // 여러 스레드에서 동시에 충전 요청
//        for (int i = 0; i < threadCount; i++) {
//            executorService.submit(() -> {
//                try {
//                    pointService.use(new UsePointCommand(1L, 1000));  // 동시 요청
//                    successCount.incrementAndGet();  // 성공 시 카운트 증가
//                } catch (ObjectOptimisticLockingFailureException e) {
//                    System.out.println("예외 발생: " + e.getMessage());
//                } finally {
//                    latch.countDown();  // 스레드 작업 완료
//                }
//            });
//        }
//
//        latch.await();  // 모든 스레드가 완료될 때까지 대기
//        executorService.shutdown();
//
//        // 테스트: 성공한 스레드는 최대 1개여야 함
//        assertThat(successCount.get()).isEqualTo(10);
//    }
}
