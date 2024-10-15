package com.hhplus.concert.exception;

import java.util.HashMap;
import java.util.Map;

public class InvalidException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getStatusCode() {
        return "404";
    }
}
