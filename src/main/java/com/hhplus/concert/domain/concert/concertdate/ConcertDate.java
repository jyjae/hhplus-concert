package com.hhplus.concert.domain.concert.concertdate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ConcertDate {

  private final Long id;
  private final Long concertId;
  private final int totalCapacity;
  private final int currentCapacity;
  private final Long startDate;
  private final String place;

  public static ConcertDate of(Long id, Long concertId, int totalCapacity, int currentCapacity,
      Long startDate, String place) {
    return new ConcertDate(id, concertId, totalCapacity, currentCapacity, startDate, place);
  }

  public boolean isAvailable() {
    return currentCapacity < totalCapacity;
  }
}
