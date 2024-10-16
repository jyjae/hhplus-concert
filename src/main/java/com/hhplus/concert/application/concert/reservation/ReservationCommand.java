package com.hhplus.concert.application.concert.reservation;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class ReservationCommand {
    private Long userId;
    private Long concertDateSeatId;
    private int price;

    public ReservationCommand(Long userId, Long concertDateSeatId, int price) {
        if (userId == null) {
            throw new InvalidException("userId is null");
        }
        if (concertDateSeatId == null) {
            throw new InvalidException("concertDateSeatId is null");
        }
        if (price < 0) {
            throw new InvalidException("price is invalid");
        }
        this.userId = userId;
        this.concertDateSeatId = concertDateSeatId;
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getConcertDateSeatId() {
        return concertDateSeatId;
    }
}
