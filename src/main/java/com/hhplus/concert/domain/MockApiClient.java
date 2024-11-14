package com.hhplus.concert.domain;


import com.hhplus.concert.domain.payment.model.Payment;
import com.hhplus.concert.domain.reservation.model.Reservation;

public interface MockApiClient {

  boolean sendSlack(Long paymentId, Long reservation, Long concertDateSeatId);
}
