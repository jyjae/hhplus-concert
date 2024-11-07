package com.hhplus.concert.domain.token.dto;

import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class GetUserQueueRankQuery {
    private final String token;
    private final Integer capacity;

    public GetUserQueueRankQuery(String token, Integer capacity) {
        if(token == null) {
            throw new InvalidException(ErrorType.INVALID_TOKEN);
        }
        if(capacity == null) {
            throw new InvalidException(ErrorType.INVALID_CAPACITY);
        }
        this.token = token;
        this.capacity = capacity;
    }
}
