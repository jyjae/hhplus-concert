package com.hhplus.concert.interfaces.api.payment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePaymentResponse {
    private Long paymentId;

    public CreatePaymentResponse(Long paymentId) {
        this.paymentId = paymentId;
    }
}
