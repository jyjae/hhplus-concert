package com.hhplus.concert.interfaces.api.concert.concertdate;

import com.hhplus.concert.application.facade.ConcertDateFacade;
import com.hhplus.concert.domain.concert.concertdate.ConcertDate;
import com.hhplus.concert.interfaces.api.concert.concertdate.dto.GetConcertDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/concerts/{concertId}/concert-dates")
public class ConcertDateController {

    private final ConcertDateFacade concertDateFacade;

    /**
     * 예약 가능한 콘서트 날짜 조회 API
     * @param concertId - 콘서트 아이디
     * @return - 콘서트 날짜 목록
     */
    @GetMapping()
    public ResponseEntity<List<ConcertDate>> getConcertDates(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId) {
        return ResponseEntity.ok(concertDateFacade.concertDates(concertId, token));
    }
}
