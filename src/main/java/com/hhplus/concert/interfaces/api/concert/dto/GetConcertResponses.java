package com.hhplus.concert.interfaces.api.concert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetConcertResponses {
    List<GetConcertResponse> concerts;
}
