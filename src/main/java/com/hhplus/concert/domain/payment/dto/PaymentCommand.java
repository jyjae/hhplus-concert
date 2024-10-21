package com.hhplus.concert.domain.payment.dto;

import lombok.Getter;

@Getter
public class PaymentCommand {
    private Long userId;
    private Long reservationId;

    public PaymentCommand(Long userId, Long reservationId) {
        if(userId == null) {
            throw new IllegalArgumentException("userId is null");
        }
        if(reservationId == null) {
            throw new IllegalArgumentException("point is null");
        }
        this.userId = userId;
        this.reservationId = reservationId;
    }
}
