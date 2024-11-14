package com.hhplus.concert.domain.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PaymentSuccessEvent {

  private final Long reservationId;
  private final Long paymentId;
  private final Long concertDateSeatId;
  private final Long userId;

  @Builder
  public PaymentSuccessEvent(Long reservationId, Long paymentId, Long concertDateSeatId, Long userId) {
    this.reservationId = reservationId;
    this.paymentId = paymentId;
    this.concertDateSeatId = concertDateSeatId;
    this.userId = userId;
  }
}
