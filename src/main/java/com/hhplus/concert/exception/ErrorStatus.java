package com.hhplus.concert.exception;

import org.springframework.boot.logging.LogLevel;

public interface ErrorStatus {

    ErrorCode getCode();
    String getMessage();
    LogLevel getLogLevel();
}