package com.hhplus.concert.application.token;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class CreateQueueTokenCommand {
    private final Long userId;
    private final String token;

    public CreateQueueTokenCommand(Long userId, String token) {
        if(userId == null) {
            throw new InvalidException("userId is null");
        }
        if(token == null) {
            throw new  InvalidException("token is null");
        }
        this.userId = userId;
        this.token = token;
    }

}
