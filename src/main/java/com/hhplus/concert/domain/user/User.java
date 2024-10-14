package com.hhplus.concert.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final Long userId;
    private final String name;
    private final String email;

    public static User of(Long userId, String name, String email) {
        return new User(userId, name, email);
    }
}
