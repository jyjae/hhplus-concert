package com.hhplus.concert.interfaces.api.concert.concertdate.seat;

import com.hhplus.concert.application.facade.ConcertDateSeatFacade;
import com.hhplus.concert.interfaces.api.concert.concertdate.seat.dto.GetConcertDateSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertDateSeatController implements ConcertDateSeatApi {

    private final ConcertDateSeatFacade concertDateSeatFacade;

    @Override
    public ResponseEntity<List<GetConcertDateSeatResponse>> getAvailableSeats(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId,
            @PathVariable(name = "concertDateId") Long concertDateId) {
        return ResponseEntity.ok(concertDateSeatFacade.concertDateSeats(token, concertDateId)
                .stream()
                .map(seat -> new GetConcertDateSeatResponse(
                        seat.getId(),
                        seat.getConcertDateId(),
                        seat.getPrice(),
                        seat.getExpiredDate(),
                        seat.getStatus())).toList());
    }
}
