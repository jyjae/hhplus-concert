package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.config.ConfigService;
import com.hhplus.concert.application.token.FindQueueTokenQuery;
import com.hhplus.concert.application.token.GetUserQueueRankQuery;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.application.user.FindUserCommand;
import com.hhplus.concert.application.user.UserService;
import com.hhplus.concert.application.user.point.GetPointQuery;
import com.hhplus.concert.application.user.point.PointService;
import com.hhplus.concert.domain.token.QueueToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final QueueTokenService queueTokenService;
    private final ConfigService configService;
    private final PointService pointService;

    public Long rank(Long userId, String token) {
//        userService.findUser(new FindUserCommand(userId));
        QueueToken queueToken = queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        int count = configService.getMaxQueueTokens();

        return queueTokenService.userRank(new GetUserQueueRankQuery(queueToken.getId(), count));
    }


    public int point(Long userId, String token) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        return pointService.point(new GetPointQuery(userId));
    }
}
