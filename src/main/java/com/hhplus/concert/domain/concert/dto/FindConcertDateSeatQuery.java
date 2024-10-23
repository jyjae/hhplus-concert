package com.hhplus.concert.domain.concert.dto;

import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.exception.InvalidException;
import lombok.Getter;

@Getter
public class FindConcertDateSeatQuery {
    private final Long concertDateId;
    private final Long concertDateSeatId;

    public FindConcertDateSeatQuery(Long concertDateId, Long concertDateSeatId) {
        if(concertDateId == null) {
            throw new InvalidException(DomainErrorType.INVALID_CONCERT_DATE);
        }
        if(concertDateSeatId == null) {
            throw new InvalidException(DomainErrorType.INVALID_CONCERT_DATE_SEAT);
        }
        this.concertDateId = concertDateId;
        this.concertDateSeatId = concertDateSeatId;
    }
}
