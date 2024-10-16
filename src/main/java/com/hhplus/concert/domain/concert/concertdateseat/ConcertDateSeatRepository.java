package com.hhplus.concert.domain.concert.concertdateseat;

import com.hhplus.concert.domain.concert.reservation.Reservation;

import java.util.List;
import java.util.Optional;

public interface ConcertDateSeatRepository {
    List<ConcertDateSeat> concertDateSeat(long concertDateId, long currentTime);

    Optional<ConcertDateSeat> findAvailableConcertDateSeat(Long concertDateId, Long concertDateSeatId);

    Optional<ConcertDateSeat> concertDateSeatById(Long concertDateSeatId);

    void save(ConcertDateSeat concertDateSeat);
}
