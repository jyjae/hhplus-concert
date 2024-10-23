package com.hhplus.concert.domain.concert.constants;

public enum ConcertDateSeatStatus {
  AVAILABLE("AVAILABLE"),
  TEMP_RESERVED("TEMP_RESERVED"),
  RESERVED("RESERVED");

    private String status;

    ConcertDateSeatStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}