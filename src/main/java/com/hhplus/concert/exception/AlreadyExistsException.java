package com.hhplus.concert.exception;

import java.util.HashMap;
import java.util.Map;

public class AlreadyExistsException extends RuntimeException{

    private final Map<String, String> validation = new HashMap<>();

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getStatusCode() {
        return "400";
    }
}
