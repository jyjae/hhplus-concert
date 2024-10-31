package com.hhplus.concert.exception;

public class LockException extends BaseException {
    private final ErrorType errorType;

    public LockException(ErrorType errorType) {
        super(errorType);
        this.errorType = errorType;
    }

    public ErrorCode getErrorStatus() {
        return errorType.getCode();
    }

    public String getMessage() {
        return errorType.getMessage();
    }

}
