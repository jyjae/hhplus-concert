package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.token.CreateQueueTokenCommand;
import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.application.user.FindUserCommand;
import com.hhplus.concert.application.user.UserService;
import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueueTokenFacade {

    private final QueueTokenService queueTokenService;

    public String token(Long userId) {
        return queueTokenService.createQueueToken(new CreateQueueTokenCommand(userId, UuidUtil.generateUuid()));
    }
}
