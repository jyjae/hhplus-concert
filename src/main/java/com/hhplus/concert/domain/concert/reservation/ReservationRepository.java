package com.hhplus.concert.domain.concert.reservation;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReservationRepository {
    Optional<Reservation> findReservation(Long reservationId);


    Long reserveConcertDateSeat(Reservation reservation);

    Optional<Reservation> findReservationById(Long reservationId);
}
