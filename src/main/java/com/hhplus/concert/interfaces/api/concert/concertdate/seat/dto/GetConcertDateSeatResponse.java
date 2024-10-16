package com.hhplus.concert.interfaces.api.concert.concertdate.seat.dto;

import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatStatus;
import lombok.Getter;

@Getter
public class GetConcertDateSeatResponse {
        private Long id;
        private Long concertDateId;
        private int price;
        private Long expiredDate;
        private ConcertDateSeatStatus status;

        public GetConcertDateSeatResponse(Long id, Long concertDateId, int price, Long expiredDate, ConcertDateSeatStatus status) {
            this.id = id;
            this.concertDateId = concertDateId;
            this.price = price;
            this.expiredDate = expiredDate;
            this.status = status;
        }
}
