package com.hhplus.concert.interfaces.api.concert.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationResponse {
    private Long reservationId;

    public ReservationResponse(Long reservationId) {
        this.reservationId = reservationId;
    }
}
