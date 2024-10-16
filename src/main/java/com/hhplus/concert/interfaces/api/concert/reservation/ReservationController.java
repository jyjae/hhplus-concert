package com.hhplus.concert.interfaces.api.concert.reservation;

import com.hhplus.concert.application.facade.ReservationFacade;
import com.hhplus.concert.interfaces.api.concert.reservation.dto.ReservationRequest;
import com.hhplus.concert.interfaces.api.concert.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationController implements ReservationApi{

    private final ReservationFacade reservationFacade;


    @Override
    public ResponseEntity<ReservationResponse> reserveConcertDateSeat(
            @RequestHeader("token") String token,
            @PathVariable(name = "concertId") Long concertId,
            @PathVariable(name = "concertDateId") Long concertDateId,
            @RequestBody ReservationRequest request) {

        return ResponseEntity.ok(new ReservationResponse(reservationFacade.reservation(token, concertDateId, request.getUserId(), request.getConcertDateSeatId())));
    }

}
