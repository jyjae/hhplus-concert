package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PointFacade {

    private final PointService pointService;

    @Transactional
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 100,
            backoff = @Backoff(100),
            listeners = {"retryLoggingListener"}
    )
    public Long charge(Long userId, Integer point) {
        return pointService.charge(new ChargePointCommand(userId, point));
    }


}
