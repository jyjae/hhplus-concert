package com.hhplus.concert.domain.reservation.repository;

import com.hhplus.concert.domain.reservation.model.Reservation;
import java.util.Optional;

public interface ReservationRepository {
    Optional<Reservation> findReservation(Long reservationId);

    Long reserveConcertDateSeat(Reservation reservation);

    Optional<Reservation> findReservationById(Long reservationId);

    void deleteReservation(Long reservationId);
}
