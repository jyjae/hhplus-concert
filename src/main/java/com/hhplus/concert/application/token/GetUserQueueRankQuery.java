package com.hhplus.concert.application.token;

import lombok.Getter;

@Getter
public class GetUserQueueRankQuery {
    private final Long tokenId;
    private final Integer capacity;

    public GetUserQueueRankQuery(Long tokenId, Integer capacity) {
        this.tokenId = tokenId;
        this.capacity = capacity;
    }
}
