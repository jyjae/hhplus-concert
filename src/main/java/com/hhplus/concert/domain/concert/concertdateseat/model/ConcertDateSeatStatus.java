package com.hhplus.concert.domain.concert.concertdateseat.model;

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