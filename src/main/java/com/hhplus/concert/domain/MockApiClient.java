package com.hhplus.concert.domain;

public interface MockApiClient {

    boolean sendSlack(Long paymentId, Long reservation, Long concertDateSeatId);
}