package com.hhplus.concert.interfaces.api.concert.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationRequest {

    private Long userId;
    private Long concertDateSeatId;

}
