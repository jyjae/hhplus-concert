package com.hhplus.concert.exception;

public abstract class BaseException extends RuntimeException {
    private final ErrorType errorType;

    public BaseException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorCode getErrorCode() {
        return errorType.getCode();
    }

    public String getMessage() {
        return errorType.getMessage();
    }
}
