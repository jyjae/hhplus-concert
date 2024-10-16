package com.hhplus.concert.application.concert.concertdateseat;

import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class FindConcertDateSeatQuery {
    private final Long concertDateId;
    private final Long concertDateSeatId;

    public FindConcertDateSeatQuery(Long concertDateId, Long concertDateSeatId) {
        if(concertDateId == null) {
            throw new InvalidException("concertDateId is null");
        }
        if(concertDateSeatId == null) {
            throw new InvalidException("concertDateSeatId is null");
        }
        this.concertDateId = concertDateId;
        this.concertDateSeatId = concertDateSeatId;
    }
}
