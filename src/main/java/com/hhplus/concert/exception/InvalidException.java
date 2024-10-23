package com.hhplus.concert.exception;

public class InvalidException extends BaseException {
    private final ErrorStatus errorCode;

    public InvalidException(ErrorStatus errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorStatus() {
        return errorCode.getCode();
    }


    public String getMessage() {
        return errorCode.getMessage();
    }
}
