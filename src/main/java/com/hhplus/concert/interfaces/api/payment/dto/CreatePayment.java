package com.hhplus.concert.interfaces.api.payment.dto;

public class CreatePayment {
    public record Request(Long userId, Long seatId) {

    }

    public record Response(Long paymentId, Long userId, Long seatId, int amount, String status) {

    }
}
