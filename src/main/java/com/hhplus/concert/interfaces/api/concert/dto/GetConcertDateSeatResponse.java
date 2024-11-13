package com.hhplus.concert.interfaces.api.concert.dto;

import com.hhplus.concert.domain.concert.constants.ConcertDateSeatStatus;
import lombok.Getter;

@Getter
public class GetConcertDateSeatResponse {
        private Long id;
        private Long concertDateId;
        private int price;
        private ConcertDateSeatStatus status;

        public GetConcertDateSeatResponse(Long id, Long concertDateId, int price, ConcertDateSeatStatus status) {
            this.id = id;
            this.concertDateId = concertDateId;
            this.price = price;
            this.status = status;
        }
}
