package com.hhplus.concert.infra.persistence.error;

import com.hhplus.concert.exception.ErrorCode;
import com.hhplus.concert.exception.ErrorStatus;
import org.springframework.boot.logging.LogLevel;

public enum PersistenceErrorType implements ErrorStatus {

    NOT_FOUND_TOKEN(ErrorCode.NOT_FOUND, "Token not found", LogLevel.INFO),
    ;

    private final ErrorCode errorCode;
    private final String message;
    private final LogLevel logLevel;

    PersistenceErrorType(ErrorCode errorCode, String message, LogLevel logLevel) {
        this.errorCode = errorCode;
        this.message = message;
        this.logLevel = logLevel;
    }


    @Override
    public ErrorCode  getCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }
}
