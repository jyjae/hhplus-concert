package com.hhplus.concert.domain.config.model;

public enum ConfigKey {
    MAX_PROCESSING_TOKEN("max-processing-token");

    private final String key;

    ConfigKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}