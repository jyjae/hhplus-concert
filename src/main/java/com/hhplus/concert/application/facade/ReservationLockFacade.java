package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.lock.DistributedLock;
import com.hhplus.concert.domain.concert.dto.FindConcertDateSeatQuery;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.domain.reservation.dto.ReservationCommand;
import com.hhplus.concert.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationLockFacade {

    private final ReservationFacade reservationFacade;

    @DistributedLock(key = "#concertDateSeatId")
    public Long reservation(Long concertDateId, Long userId, Long concertDateSeatId) {
        return reservationFacade.reservation(concertDateId, userId, concertDateSeatId);
    }
}
