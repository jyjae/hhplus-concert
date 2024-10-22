package com.hhplus.concert.interfaces.api.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationRequest {

    private Long userId;
    private Long concertId;
    private Long concertDateId;
    private Long concertDateSeatId;

}
