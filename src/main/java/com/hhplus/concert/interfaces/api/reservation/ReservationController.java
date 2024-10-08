package com.hhplus.concert.interfaces.api.reservation;

import com.hhplus.concert.interfaces.api.reservation.dto.CreateReservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    /**
     * 좌석 예약
     * @return - 좌석 예약 정보
     * @param request - 유저 아이디, 좌석 아이디
     */
    @PostMapping
    public ResponseEntity<CreateReservation.Response> createReservation(
            @RequestHeader("token") String token,
            @RequestBody CreateReservation.Request request) {
        return ResponseEntity.ok(new CreateReservation.Response(1L, 1L, 1L, 100000, "CONFIRMED"));
    }
}
