package com.hhplus.concert.interfaces.api.payment.reservation;

import com.hhplus.concert.interfaces.api.payment.reservation.dto.CreateReservationCommand;
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
    public ResponseEntity<Void> createReservation(
            @RequestHeader("token") String token,
            @RequestBody CreateReservationCommand request) {
        return ResponseEntity.ok(null);
    }
}
