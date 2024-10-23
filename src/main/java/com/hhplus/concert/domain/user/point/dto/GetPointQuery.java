package com.hhplus.concert.domain.user.point.dto;

import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class GetPointQuery {
    private final Long userId;

    public GetPointQuery(Long userId) {
        if(userId == null) {
            throw new InvalidException(DomainErrorType.INVALID_USER_ID);
        }
        this.userId = userId;
    }
}
