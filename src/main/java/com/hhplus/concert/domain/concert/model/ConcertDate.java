package com.hhplus.concert.domain.concert.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class ConcertDate {

  private final Long id;
  private final Long concertId;
  private final int totalCapacity;
  private final int currentCapacity;
  private final Long startDate;
  private final String place;

  public static ConcertDate of(Long id, Long concertId, int totalCapacity, int currentCapacity,
      Long startDate, String place) {
    return ConcertDate.builder()
        .id(id)
        .concertId(concertId)
        .totalCapacity(totalCapacity)
        .currentCapacity(currentCapacity)
        .startDate(startDate)
        .place(place)
        .build();
  }

  public boolean isAvailable() {
    return currentCapacity < totalCapacity;
  }
}
