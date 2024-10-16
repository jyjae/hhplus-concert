package com.hhplus.concert.interfaces.api.concert.reservation;

import com.hhplus.concert.application.facade.ReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/concerts/{concertId}/concert-dates/{concertDateId}/reservations")
public class ReservationController {

    private final ReservationFacade reservationFacade;


    @PostMapping()
    public ResponseEntity<ReservationResponse> reserveConcertDateSeat(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId,
            @PathVariable(name = "concertDateId") Long concertDateId,
            @RequestBody ReservationRequest request) {

        return ResponseEntity.ok(new ReservationResponse(reservationFacade.reservation(token, concertDateId, request)));
    }

}