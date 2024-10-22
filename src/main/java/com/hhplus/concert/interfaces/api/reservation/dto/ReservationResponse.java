package com.hhplus.concert.interfaces.api.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationResponse {
    private Long reservationId;

    public ReservationResponse(Long reservationId) {
        this.reservationId = reservationId;
    }
}
