package com.hhplus.concert.application.token;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class FindQueueTokenQuery {
    private final String token;

    public FindQueueTokenQuery(String token) {
        if(token == null) {
            throw new InvalidException("token is null");
        }
        this.token = token;
    }

}
