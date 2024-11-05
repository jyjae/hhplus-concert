package com.hhplus.concert.application.dto;

import com.hhplus.concert.domain.concert.model.Concert;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Concerts {

    private com.hhplus.concert.domain.concert.dto.Concerts concerts;

    @Builder
    public Concerts(com.hhplus.concert.domain.concert.dto.Concerts concerts) {
        this.concerts = concerts;
    }
}
