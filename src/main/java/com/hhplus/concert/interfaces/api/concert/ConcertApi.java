package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.interfaces.api.concert.dto.GetConcertResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Concert", description = "콘서트 관련 API")
@RequestMapping("/concerts")
public interface ConcertApi {

    @Operation(
            summary = "콘서트 전체 조회",
            description = "모든 콘서트 정보를 조회합니다."
    )
    @GetMapping
    ResponseEntity<List<GetConcertResponse>> getConcerts();
}
