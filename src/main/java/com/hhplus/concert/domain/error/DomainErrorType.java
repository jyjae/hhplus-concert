package com.hhplus.concert.domain.error;

import com.hhplus.concert.exception.ErrorCode;
import com.hhplus.concert.exception.ErrorStatus;
import org.springframework.boot.logging.LogLevel;

public enum DomainErrorType implements ErrorStatus {
    SEAT_ALREADY_RESERVED(ErrorCode.ALREADY_EXISTS, "Seat already reserved", LogLevel.INFO),
    NOT_FOUND_CONCERT_DATE_SEAT(ErrorCode.NOT_FOUND, "No concert date seat information",LogLevel.INFO ),
    INVALID_CONCERT_DATE(ErrorCode.INVALID_PARAMETER, "concertDateId is null", LogLevel.INFO),
    INVALID_CONCERT_DATE_SEAT(ErrorCode.INVALID_PARAMETER, "concertDateSeatId is null", LogLevel.INFO),
    NOT_FOUND_RESERVATION(ErrorCode.NOT_FOUND, "no reservation information", LogLevel.INFO),
    RESERVATION_EXPIRED(ErrorCode.ALREADY_EXISTS, "reservation has expired", LogLevel.INFO),
    NOT_FOUND_USER(ErrorCode.NOT_FOUND, "User not found", LogLevel.INFO),
    INVALID_USER_ID(ErrorCode.INVALID_PARAMETER, "userId is null", LogLevel.INFO),
    INVALID_CONCERT_DATE_SEAT_ID(ErrorCode.INVALID_PARAMETER, "concertDateSeatId is null", LogLevel.INFO),
    INVALID_PRICE(ErrorCode.INVALID_PARAMETER, "price is invalid", LogLevel.INFO),
    INVALID_TOKEN(ErrorCode.INVALID_PARAMETER, "token is null", LogLevel.INFO),
    INVALID_CAPACITY(ErrorCode.INVALID_PARAMETER, "capacity is null", LogLevel.INFO),
    INVALID_POINT(ErrorCode.INVALID_PARAMETER, "point is invalid", LogLevel.INFO),
    NOT_ENOUGH_POINT(ErrorCode.INVALID_PARAMETER, "Not enough point", LogLevel.INFO),;

    private final ErrorCode code;
    private final String message;
    private final LogLevel logLevel;

    DomainErrorType(ErrorCode code, String message, LogLevel logLevel) {
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }

    @Override
    public ErrorCode getCode() {
        return code;
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
