package com.hhplus.concert.application.token;

import lombok.Getter;

@Getter
public class GetQueueTokenCommand {
    private Long userId;

    public GetQueueTokenCommand(Long userId) {
        if(userId == null) {
            throw new IllegalArgumentException("userId is null");
        }
        this.userId = userId;
    }
}
