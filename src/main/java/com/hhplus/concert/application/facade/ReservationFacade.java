package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.concertdateseat.ConcertDateSeatService;
import com.hhplus.concert.application.concert.concertdateseat.FindConcertDateSeatQuery;
import com.hhplus.concert.application.concert.reservation.ReservationCommand;
import com.hhplus.concert.application.concert.reservation.ReservationService;
import com.hhplus.concert.application.token.FindQueueTokenQuery;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.interfaces.api.concert.reservation.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationFacade {

    private final ReservationService reservationService;
    private final ConcertDateSeatService concertDateSeatService;
    private final QueueTokenService queueTokenService;

    public Long reservation(String token, Long concertDateId, ReservationRequest request) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        ConcertDateSeat concertDateSeat = concertDateSeatService.findAvailableConcertDateSeat(new FindConcertDateSeatQuery(concertDateId, request.getConcertDateSeatId()));
        return reservationService.reserveConcertDateSeat(new ReservationCommand(request.getUserId(), request.getConcertDateSeatId(), concertDateSeat.getPrice()));
    }
}
