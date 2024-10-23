package com.hhplus.concert.interfaces.api.error;

public record ErrorResponse(
        int code,
        String message
) {
}
