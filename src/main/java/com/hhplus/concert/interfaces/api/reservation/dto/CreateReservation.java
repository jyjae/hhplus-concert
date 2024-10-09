package com.hhplus.concert.interfaces.api.reservation.dto;

public class CreateReservation {
    public record Request(Long userId, Long seatId) {
    }
    public record Response(Long userId, Long seatId, Long reservationId, int price, String status) {
    }

}
