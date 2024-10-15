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

  public static ConcertDateSeat of(Long concertDateId, Long id, int price, Long expiredDate,
      ConcertDateSeatStatus status) {
    return new ConcertDateSeat(concertDateId, id, price, expiredDate, status);
  }

    public boolean isAvailable() {
        return status == ConcertDateSeatStatus.AVAILABLE;
    }
}
