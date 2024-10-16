package com.hhplus.concert.domain.concert.concertdateseat;

public enum ConcertDateSeatStatus {
  AVAILABLE("AVAILABLE"),
  RESERVED("RESERVED");

    private String status;

    ConcertDateSeatStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}