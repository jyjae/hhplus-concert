package com.hhplus.concert.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NotFoundException  extends BaseException {
    private final ErrorType errorType;

    public NotFoundException(ErrorType errorType) {
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