package com.hhplus.concert.domain.concert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Concert {

  private final Long id;
  private final String name;
  private final Long startDate;
  private final Long endDate;

  public static Concert of(Long id, String name, Long startDate, Long endDate) {
    return new Concert(id, name, startDate, endDate);
  }

}
