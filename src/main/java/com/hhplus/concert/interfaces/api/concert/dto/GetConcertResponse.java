package com.hhplus.concert.interfaces.api.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetConcertResponse {
    private Long id;
    private String name;
    private Long startDate;
    private Long endDate;
}
