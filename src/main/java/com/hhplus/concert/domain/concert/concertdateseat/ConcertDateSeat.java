package com.hhplus.concert.domain.concert.concertdateseat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcertDateSeat {

  private Long id;
  private Long concertDateId;
  private int price;
  private Long expiredDate;
  private ConcertDateSeatStatus status;

  public static ConcertDateSeat of(Long id, Long concertDateId, int price, Long expiredDate,
      ConcertDateSeatStatus status) {
    return new ConcertDateSeat(id, concertDateId, price, expiredDate, status);
  }

    public boolean isAvailable() {
        return status == ConcertDateSeatStatus.AVAILABLE;
    }

  public ConcertDateSeat completeReservation() {
    this.status = ConcertDateSeatStatus.RESERVED;
    return this;
  }
}
