package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
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
