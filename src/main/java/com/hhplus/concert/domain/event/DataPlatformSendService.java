package com.hhplus.concert.domain.event;

import com.hhplus.concert.domain.MockApiClient;
import com.hhplus.concert.domain.payment.model.Payment;
import com.hhplus.concert.domain.reservation.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataPlatformSendService {

  private final MockApiClient mockApiClient;

  public void sendSlack(Long paymentId, Long reservationId, Long concertDateSeatId) {
    //slack API 예약 및 결제 정보 전송
    mockApiClient.sendSlack(paymentId, reservationId, concertDateSeatId);
  }
}