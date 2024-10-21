package com.hhplus.concert.domain.concert.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Concert {

  private final Long id;
  private final String name;
  private final Long startDate;
  private final Long endDate;

  public static Concert of(Long id, String name, Long startDate, Long endDate) {
    return Concert.builder()
            .id(id)
            .name(name)
            .startDate(startDate)
            .endDate(endDate)
            .build();
  }

}
