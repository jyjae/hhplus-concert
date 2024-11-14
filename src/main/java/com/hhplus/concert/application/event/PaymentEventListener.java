package com.hhplus.concert.application.event;

import com.hhplus.concert.domain.event.DataPlatformSendService;
import com.hhplus.concert.domain.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

  private final DataPlatformSendService sendService;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void paymentSuccessHandler(PaymentSuccessEvent event) {
    sendService.sendSlack(event.getPaymentId(), event.getReservationId(), event.getConcertDateSeatId());
  }
}