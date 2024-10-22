package com.hhplus.concert.exception;

public enum ErrorCode {
    INVALID_PARAMETER(400),
    NOT_FOUND(404),
    ALREADY_EXISTS(409),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}