package com.hhplus.concert.application.user;


public class FindUserCommand {
    private Long userId;

    public FindUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long userId() {
        return userId;
    }

}
