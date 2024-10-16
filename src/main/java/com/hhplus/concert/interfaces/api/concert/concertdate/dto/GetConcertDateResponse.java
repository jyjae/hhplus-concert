package com.hhplus.concert.interfaces.api.concert.concertdate.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetConcertDateResponse {
    private Long id;
    private Long concertId;
    private int totalCapacity;
    private int currentCapacity;
    private Long startDate;
    private String place;


}
