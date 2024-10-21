package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.application.facade.ConcertFacade;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateSeatResponse;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateResponse;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertController implements ConcertApi {

    private final ConcertFacade concertFacade;

    /**
     * 콘서트 전체 조회 API
     * @return - 콘서트 목록
     */
    @Override
    public ResponseEntity<List<GetConcertResponse>> getConcerts() {
        return ResponseEntity.ok(concertFacade.concerts().stream()
                .map(concert -> new GetConcertResponse(
                        concert.getId(),
                        concert.getName(),
                        concert.getStartDate(),
                        concert.getEndDate())).toList());
    }

    /**
     * 예약 가능한 콘서트 날짜 조회 API
     * @param concertId - 콘서트 아이디
     * @return - 콘서트 날짜 목록
     */
    @Override
    public ResponseEntity<List<GetConcertDateResponse>> getConcertDates(
            @PathVariable(name = "concertId") Long concertId) {
        return ResponseEntity.ok(concertFacade.concertDates(concertId)
                .stream()
                .map(date -> new GetConcertDateResponse(
                        date.getId(),
                        date.getConcertId(),
                        date.getTotalCapacity(),
                        date.getCurrentCapacity(),
                        date.getStartDate(),
                        date.getPlace())).toList());
    }

    @Override
    public ResponseEntity<List<GetConcertDateSeatResponse>> getAvailableSeats(
            @PathVariable(name = "concertId") Long concertId,
            @PathVariable(name = "concertDateId") Long concertDateId) {
        return ResponseEntity.ok(concertFacade.concertDateSeats(concertDateId)
                .stream()
                .map(seat -> new GetConcertDateSeatResponse(
                        seat.getId(),
                        seat.getConcertDateId(),
                        seat.getPrice(),
                        seat.getExpiredDate(),
                        seat.getStatus())).toList());
    }

}
