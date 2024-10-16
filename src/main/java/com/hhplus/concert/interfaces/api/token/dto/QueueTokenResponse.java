package com.hhplus.concert.interfaces.api.token.dto;

public class QueueTokenResponse {
    private String token;

    public QueueTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
