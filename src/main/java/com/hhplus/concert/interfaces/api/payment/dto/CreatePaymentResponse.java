package com.hhplus.concert.interfaces.api.payment.dto;

import lombok.Getter;

@Getter
public class CreatePaymentResponse {
    private final Long paymentId;

    public CreatePaymentResponse(Long paymentId) {
        this.paymentId = paymentId;
    }
}
