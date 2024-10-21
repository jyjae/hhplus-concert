package com.hhplus.concert.domain.user.dto;


public class FindUserCommand {
    private Long userId;

    public FindUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long userId() {
        return userId;
    }

}
