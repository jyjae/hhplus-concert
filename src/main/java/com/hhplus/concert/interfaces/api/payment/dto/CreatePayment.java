package com.hhplus.concert.interfaces.api.payment.dto;

public class CreatePayment {
    public record Request(Long reservationId) {

    }

    public record Response(Long paymentId, Long reservationId, int amount, String status) {

    }
}
