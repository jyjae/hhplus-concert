package com.hhplus.concert.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NotFoundException  extends BaseException {
    private final ErrorStatus errorStatus;

    public NotFoundException(ErrorStatus errorStatus) {
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