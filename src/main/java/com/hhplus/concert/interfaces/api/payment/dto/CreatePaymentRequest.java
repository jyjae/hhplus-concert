package com.hhplus.concert.interfaces.api.payment.dto;

import lombok.Getter;

@Getter
public class CreatePaymentRequest {
    private final Long userId;
    private final Long reservationId;

    public CreatePaymentRequest(Long userId, Long reservationId) {
        this.userId = userId;
        this.reservationId = reservationId;
    }
}
