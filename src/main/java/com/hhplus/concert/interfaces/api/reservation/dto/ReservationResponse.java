package com.hhplus.concert.interfaces.api.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationResponse {
    private Long reservationId;

    public ReservationResponse(Long reservationId) {
        this.reservationId = reservationId;
    }
}
