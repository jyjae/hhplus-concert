package com.hhplus.concert.interfaces.api.concert.concertdate.dto;


import java.time.LocalDateTime;

public class GetConcertDate {
    public record Response(Long id, int total_capacity, int current_capacity, LocalDateTime concert_date) {

    }
}
