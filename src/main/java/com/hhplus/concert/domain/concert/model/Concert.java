package com.hhplus.concert.domain.concert.model;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Builder
public class Concert {

  private Long id;
  private String name;
  private Long startDate;
  private Long endDate;

  public static Concert of(Long id, String name, Long startDate, Long endDate) {
    return Concert.builder()
            .id(id)
            .name(name)
            .startDate(startDate)
            .endDate(endDate)
            .build();
  }

}
