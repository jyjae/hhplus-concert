package com.hhplus.concert.domain.token.dto;

import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class CreateQueueTokenCommand {
    private final Long userId;
    private final String token;

    public CreateQueueTokenCommand(Long userId, String token) {
        if(userId == null) {
            throw new InvalidException(ErrorType.INVALID_USER_ID);
        }
        if(token == null) {
            throw new  InvalidException(ErrorType.INVALID_TOKEN);
        }
        this.userId = userId;
        this.token = token;
    }

}
