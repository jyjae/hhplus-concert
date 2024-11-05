package com.hhplus.concert.domain.concert.dto;

import com.hhplus.concert.domain.concert.model.Concert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Concerts {

    List<Concert> concerts;

    public Concerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
