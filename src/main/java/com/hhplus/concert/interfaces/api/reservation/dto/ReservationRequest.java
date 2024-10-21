package com.hhplus.concert.interfaces.api.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationRequest {

    private Long userId;
    private Long concertId;
    private Long concertDateId;
    private Long concertDateSeatId;

}
