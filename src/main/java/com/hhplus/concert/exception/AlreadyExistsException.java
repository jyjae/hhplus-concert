package com.hhplus.concert.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends BaseException {
    private final ErrorType errorType;

    public AlreadyExistsException(ErrorType errorType) {
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
