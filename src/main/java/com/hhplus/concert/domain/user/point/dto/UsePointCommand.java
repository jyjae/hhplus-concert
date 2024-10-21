package com.hhplus.concert.domain.user.point.dto;

import lombok.Getter;

@Getter
public class UsePointCommand {
    private Long userId;
    private int point;

    public UsePointCommand(Long userId, int point) {
        this.userId = userId;
        this.point = point;
    }
}
