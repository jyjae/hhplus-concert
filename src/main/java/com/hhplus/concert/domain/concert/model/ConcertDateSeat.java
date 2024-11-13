package com.hhplus.concert.domain.concert.model;

import com.hhplus.concert.domain.concert.constants.ConcertDateSeatStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ConcertDateSeat {

  private Long id;
  private Long concertDateId;
  private int price;
  private ConcertDateSeatStatus status;

  public static ConcertDateSeat of(Long id, Long concertDateId, int price, ConcertDateSeatStatus status) {
    return ConcertDateSeat.builder()
        .id(id)
        .concertDateId(concertDateId)
        .price(price)
        .status(status)
        .build();
  }

    public boolean isAvailable() {
        return status == ConcertDateSeatStatus.AVAILABLE;
    }

  public ConcertDateSeat completeReservation() {
    return ConcertDateSeat.builder()
        .id(id)
        .concertDateId(concertDateId)
        .price(price)
        .status(ConcertDateSeatStatus.RESERVED)
        .build();
  }

  public void tempReservation() {
    this.status = ConcertDateSeatStatus.TEMP_RESERVED;
  }
}
