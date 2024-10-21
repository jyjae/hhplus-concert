package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.GetPointQuery;
import com.hhplus.concert.domain.user.point.service.PointService;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointFacade {

    private final PointService pointService;
    private final QueueTokenService queueTokenService;

    public Long charge(String token, Long userId, ChargePointRequest request) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        return pointService.charge(new ChargePointCommand(userId, request.getPoint()));
    }

    public Integer point(String token, Long userId) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        return pointService.point(new GetPointQuery(userId));
    }
}
