package com.hhplus.concert.application.user.point;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class GetPointQuery {
    private final Long userId;

    public GetPointQuery(Long userId) {
        if(userId == null) {
            throw new InvalidException("userId is null");
        }
        this.userId = userId;
    }
}
