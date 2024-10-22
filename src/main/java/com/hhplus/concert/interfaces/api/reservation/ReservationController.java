package com.hhplus.concert.interfaces.api.reservation;

import com.hhplus.concert.application.facade.ReservationFacade;
import com.hhplus.concert.interfaces.api.reservation.dto.ReservationRequest;
import com.hhplus.concert.interfaces.api.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationController implements ReservationApi {

    private final ReservationFacade reservationFacade;

    @Override
    public ResponseEntity<ReservationResponse> reserveConcertDateSeat(
            @RequestBody ReservationRequest request) {

        return ResponseEntity.ok(new ReservationResponse(
                reservationFacade.reservation(
                        request.getConcertDateId(),
                        request.getUserId(),
                        request.getConcertDateSeatId())
        ));
    }

}
