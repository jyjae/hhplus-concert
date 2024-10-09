package com.hhplus.concert.interfaces.api.seat.dto;

public class GetConcertSeat {

        public record Response(Long seatId, String seatNumber, int seatPrice) {

        }
}
