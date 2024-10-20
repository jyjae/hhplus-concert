package com.hhplus.concert.domain.payment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Payment {
    private final Long id;
    private final Long userId;
    private final Long reservationId;
    private final PaymentStatus status;


    public static Payment of(Long userId, Long reservationId) {
        return Payment.builder()
                .userId(userId)
                .reservationId(reservationId)
                .build();
    }
}
