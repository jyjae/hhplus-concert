package com.hhplus.concert.domain.token.dto;

import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class FindQueueTokenQuery {
    private final String token;

    public FindQueueTokenQuery(String token) {
        if(token == null) {
            throw new InvalidException(ErrorType.INVALID_TOKEN);
        }
        this.token = token;
    }

}
