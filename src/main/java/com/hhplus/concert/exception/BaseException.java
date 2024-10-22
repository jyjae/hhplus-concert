package com.hhplus.concert.exception;

public abstract class BaseException extends RuntimeException {
    private final ErrorStatus errorStatus;

    public BaseException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorCode getErrorCode() {
        return errorStatus.getCode();
    }

    public String getMessage() {
        return errorStatus.getMessage();
    }
}
