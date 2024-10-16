package com.hhplus.concert.interfaces.api.concert.concertdate;

import com.hhplus.concert.application.facade.ConcertDateFacade;
import com.hhplus.concert.interfaces.api.concert.concertdate.dto.GetConcertDateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertDateController implements ConcertDateApi {

    private final ConcertDateFacade concertDateFacade;

    /**
     * 예약 가능한 콘서트 날짜 조회 API
     * @param concertId - 콘서트 아이디
     * @return - 콘서트 날짜 목록
     */
   @Override
    public ResponseEntity<List<GetConcertDateResponse>> getConcertDates(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId) {
        return ResponseEntity.ok(concertDateFacade.concertDates(concertId, token)
                .stream()
                .map(date -> new GetConcertDateResponse(
                        date.getId(),
                        date.getConcertId(),
                        date.getTotalCapacity(),
                        date.getCurrentCapacity(),
                        date.getStartDate(),
                        date.getPlace())).toList());
    }
}
