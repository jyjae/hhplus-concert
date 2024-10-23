package com.hhplus.concert.domain.token.dto;

import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class GetUserQueueRankQuery {
    private final Long tokenId;
    private final Integer capacity;

    public GetUserQueueRankQuery(Long tokenId, Integer capacity) {
        if(tokenId == null) {
            throw new InvalidException(DomainErrorType.INVALID_TOKEN);
        }
        if(capacity == null) {
            throw new InvalidException(DomainErrorType.INVALID_CAPACITY);
        }
        this.tokenId = tokenId;
        this.capacity = capacity;
    }
}
