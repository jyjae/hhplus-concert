package com.hhplus.concert.domain.token;

public enum QueueTokenStatus {

    WAITING("WAITING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    EXPIRED("EXPIRED");

    private final String value;

    QueueTokenStatus(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
}
