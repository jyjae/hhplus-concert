package com.hhplus.concert.domain.payment.model.history;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PaymentHistory {
    private Long id;
    private Long paymentId;
    private Long userId;
    private Long concertDateSeatId;

    public static PaymentHistory of(Long paymentId, Long userId, Long concertDateSeatId) {
        return PaymentHistory.builder()
                .paymentId(paymentId)
                .userId(userId)
                .concertDateSeatId(concertDateSeatId)
                .build();
    }
}
