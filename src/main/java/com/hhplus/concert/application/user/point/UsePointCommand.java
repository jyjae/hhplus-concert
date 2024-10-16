package com.hhplus.concert.application.user.point;

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
