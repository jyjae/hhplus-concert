package com.hhplus.concert.domain.concert.reservation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reservation {
    private final Long id;
    private final Long userId;
    private final int price;
    private final Long concertDateSeatId;
    private final Long reservationDate;
    private final Long expirationDate; // reservationDate + 5 minutes


    public static Reservation from(Long id, Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        return Reservation.builder()
                .id(id)
                .userId(userId)
                .price(price)
                .concertDateSeatId(concertDateSeatId)
                .reservationDate(reservationDate)
                .expirationDate(expirationDate)
                .build();
    }

    public static Reservation from(Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        return Reservation.builder()
                .userId(userId)
                .price(price)
                .concertDateSeatId(concertDateSeatId)
                .reservationDate(reservationDate)
                .expirationDate(expirationDate)
                .build();
    }


    public boolean isExpired(long currentTimestamp) {
        return currentTimestamp > expirationDate;
    }

}
