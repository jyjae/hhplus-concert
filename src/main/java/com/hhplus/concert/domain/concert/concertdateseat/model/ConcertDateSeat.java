package com.hhplus.concert.domain.concert.concertdateseat.model;

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
  private Long expiredDate;
  private ConcertDateSeatStatus status;

  public static ConcertDateSeat of(Long id, Long concertDateId, int price, Long expiredDate,
      ConcertDateSeatStatus status) {
    return ConcertDateSeat.builder()
        .id(id)
        .concertDateId(concertDateId)
        .price(price)
        .expiredDate(expiredDate)
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
        .expiredDate(expiredDate)
        .status(ConcertDateSeatStatus.RESERVED)
        .build();
  }
}
