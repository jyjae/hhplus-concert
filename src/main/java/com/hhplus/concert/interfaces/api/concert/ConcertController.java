package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.interfaces.api.concert.dto.GetConcert;
import com.hhplus.concert.interfaces.api.user.dto.GetUserQueueRank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {


    /**
     * 콘서트 전체 조회 API
     * @return - 콘서트 목록
     */
    @GetMapping
    public ResponseEntity<List<GetConcert.Response>> getConcerts() {
        List<GetConcert.Response> concerts = List.of(
                new GetConcert.Response(1L, "아이유 가을 콘서트"),
                new GetConcert.Response(2L, "방탄소년단 제대 콘서트")
        );
        return ResponseEntity.ok(concerts);
    }

}
