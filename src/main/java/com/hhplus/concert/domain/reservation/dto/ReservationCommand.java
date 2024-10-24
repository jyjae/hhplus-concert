package com.hhplus.concert.domain.reservation.dto;

import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class ReservationCommand {
    private Long userId;
    private Long concertDateSeatId;
    private int price;

    public ReservationCommand(Long userId, Long concertDateSeatId, int price) {
        if (userId == null) {
            throw new InvalidException(ErrorType.INVALID_USER_ID);
        }
        if (concertDateSeatId == null) {
            throw new InvalidException(ErrorType.INVALID_CONCERT_DATE_SEAT_ID);
        }
        if (price < 0) {
            throw new InvalidException(ErrorType.INVALID_PRICE);
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
