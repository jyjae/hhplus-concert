package com.hhplus.concert.domain.user.point.dto;

import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class ChargePointCommand {
    private Long userId;
    private int point;

    public ChargePointCommand(Long userId, int point) {
        if(userId == null) {
            throw new InvalidException(DomainErrorType.INVALID_USER_ID);
        }
        if(point < 0) {
            throw new InvalidException(DomainErrorType.INVALID_POINT);
        }
        this.userId = userId;
        this.point = point;
    }
}
