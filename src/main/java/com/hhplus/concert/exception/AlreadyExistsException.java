package com.hhplus.concert.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends BaseException {
    private final ErrorStatus errorStatus;

    public AlreadyExistsException(ErrorStatus errorStatus) {
        super(errorStatus);
        this.errorStatus = errorStatus;
    }

    public ErrorCode getErrorStatus() {
        return errorStatus.getCode();
    }

    public String getMessage() {
        return errorStatus.getMessage();
    }

}
