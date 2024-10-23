package com.hhplus.concert.exception;

public class InvalidException extends BaseException {
    private final ErrorType errorType;

    public InvalidException(ErrorType errorType) {
        super(errorType);
        this.errorType = errorType;
    }

    public ErrorCode getErrorType() {
        return errorType.getCode();
    }


    public String getMessage() {
        return errorType.getMessage();
    }
}
