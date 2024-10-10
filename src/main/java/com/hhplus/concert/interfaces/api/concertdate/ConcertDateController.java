package com.hhplus.concert.interfaces.api.concertdate;

import com.hhplus.concert.interfaces.api.concert.dto.GetConcert;
import com.hhplus.concert.interfaces.api.concertdate.dto.GetConcertDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/concert-dates")
public class ConcertDateController {

    /**
     * 예약 가능한 콘서트 날짜 조회 API
     * @param concertId - 콘서트 아이디
     * @return - 콘서트 날짜 목록
     */
    @GetMapping("/{concertId}")
    public ResponseEntity<List<GetConcertDate.Response>> getConcertDates(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") String concertId) {
        List<GetConcertDate.Response> concertDates = List.of(
                new GetConcertDate.Response(1L,  50, 30, LocalDateTime.now()),
                new GetConcertDate.Response(2L,  50, 10, LocalDateTime.now()),
                new GetConcertDate.Response(3L,  50, 0, LocalDateTime.now())
        );
        return ResponseEntity.ok(concertDates);
    }
}
