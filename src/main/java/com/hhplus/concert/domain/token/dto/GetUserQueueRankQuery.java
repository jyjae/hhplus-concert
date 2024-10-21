package com.hhplus.concert.domain.token.dto;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class GetUserQueueRankQuery {
    private final Long tokenId;
    private final Integer capacity;

    public GetUserQueueRankQuery(Long tokenId, Integer capacity) {
        if(tokenId == null) {
            throw new InvalidException("tokenId is null");
        }
        if(capacity == null) {
            throw new InvalidException("capacity is null");
        }
        this.tokenId = tokenId;
        this.capacity = capacity;
    }
}
