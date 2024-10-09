package com.hhplus.concert.interfaces.api.seat;

import com.hhplus.concert.interfaces.api.seat.dto.GetConcertSeat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/concert-seats")
public class ConcertSeatController {

    /**
     * 좌석 조회 API
     * @param concertDateId - 콘서트 날짜 아이디
     * @return - 좌석 목록
     */
    @GetMapping("/{concertDateId}")
    public ResponseEntity<List<GetConcertSeat.Response>> getSeats(@PathVariable(name = "concertDateId") Long concertDateId) {
        List<GetConcertSeat.Response> seats = List.of(
                new GetConcertSeat.Response(1L, "AAS-1", 100000),
                new GetConcertSeat.Response(2L, "AAS-2", 150000),
                new GetConcertSeat.Response(3L, "AAS-3", 200000),
                new GetConcertSeat.Response(4L, "AAS-4", 100000)
        );
        return ResponseEntity.ok(seats);
    }


}
