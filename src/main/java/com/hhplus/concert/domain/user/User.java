package com.hhplus.concert.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    private final Long userId;
    private final String name;
    private final String email;

    public static User of(Long userId, String name, String email) {
        return User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .build();
    }
}
