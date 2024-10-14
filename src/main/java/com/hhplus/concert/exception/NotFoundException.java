package com.hhplus.concert.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class NotFoundException  extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getStatusCode() {
        return "404";
    }
}