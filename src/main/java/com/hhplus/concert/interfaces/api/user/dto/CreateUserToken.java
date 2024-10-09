package com.hhplus.concert.interfaces.api.user.dto;

public class CreateUserToken {

    public record Request(String userId) {

    }

    public record Response(String token) {

    }
}
