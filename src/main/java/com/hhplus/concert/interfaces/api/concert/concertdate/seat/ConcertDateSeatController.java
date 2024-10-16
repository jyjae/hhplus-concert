package com.hhplus.concert.interfaces.api.concert.concertdate.seat;

import com.hhplus.concert.application.facade.ConcertDateSeatFacade;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.interfaces.api.concert.concertdate.seat.dto.GetConcertDateSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/concerts/{concertId}/concert-dates/{concertDateId}/seats")
public class ConcertDateSeatController {

    private final ConcertDateSeatFacade concertDateSeatFacade;

    @GetMapping()
    public ResponseEntity<List<GetConcertDateSeatResponse>> getAvailableSeats(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId,
            @PathVariable(name = "concertDateId") Long concertDateId) {
        return ResponseEntity.ok(concertDateSeatFacade.concertDateSeats(token, concertDateId)
                .stream()
                .map(seat -> new GetConcertDateSeatResponse(seat.getId(), seat.getConcertDateId(), seat.getPrice(), seat.getExpiredDate(), seat.getStatus()))
                .toList());
    }
}
