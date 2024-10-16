package com.hhplus.concert.common;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class TimeProvider{

    public long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    public long getCurrentInstantPlusMinutes(int minutes) {
        return Instant.now().plus(
                Duration.ofMinutes(minutes)).toEpochMilli();
    }
}
