package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.lock.DistributedLock;
import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.domain.concert.dto.FindConcertDateSeatQuery;
import com.hhplus.concert.domain.reservation.dto.ReservationCommand;
import com.hhplus.concert.domain.reservation.service.ReservationService;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationFacade {

    private final ReservationService reservationService;
    private final ConcertDateSeatService concertDateSeatService;

    @Transactional
    public Long reservation(Long concertDateId, Long userId, Long concertDateSeatId) {
        ConcertDateSeat concertDateSeat = concertDateSeatService.findAvailableConcertDateSeat(new FindConcertDateSeatQuery(concertDateId, concertDateSeatId));
        Long reservationId = reservationService.reserveConcertDateSeat(new ReservationCommand(userId, concertDateSeatId, concertDateSeat.getPrice()));
        concertDateSeatService.tempReservation(concertDateSeat);
        return reservationId;
    }
}
