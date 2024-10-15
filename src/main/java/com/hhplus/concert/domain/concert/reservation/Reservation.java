package com.hhplus.concert.domain.concert.reservation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation {
    private final Long id;
    private final Long userId;
    private final int price;
    private final Long concertDateSeatId;
    private final Long reservationDate;
    private final Long expirationDate; // reservationDate + 5 minutes

    public Reservation(Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        this(null, userId, price, concertDateSeatId, reservationDate, expirationDate);
    }


    public static Reservation from(Long id, Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        return new Reservation(id, userId, price, concertDateSeatId, reservationDate, expirationDate);
    }

    public static Reservation from(Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        return new Reservation(userId, price, concertDateSeatId, reservationDate, expirationDate);
    }


    public boolean isExpired(long currentTimestamp) {
        return currentTimestamp > expirationDate;
    }
}
