package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.config.service.ConfigService;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.dto.GetUserQueueRankQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.user.point.dto.GetPointQuery;
import com.hhplus.concert.domain.user.point.service.PointService;
import com.hhplus.concert.domain.token.model.QueueToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final QueueTokenService queueTokenService;
    private final ConfigService configService;
    private final PointService pointService;

    public Long rank(Long userId, String token) {
        QueueToken queueToken = queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        int count = configService.getMaxQueueTokens();

        return queueTokenService.userRank(new GetUserQueueRankQuery(queueToken.getId(), count));
    }


    public int point(Long userId, String token) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        return pointService.point(new GetPointQuery(userId));
    }
}
