package com.hhplus.concert.domain.concert.reservation;

import java.util.Optional;

public interface ReservationRepository {
    Optional<Reservation> findReservationExpiredDateAfter(Long reservationId, Long currentTime);


    Long reserveConcertDateSeat(Reservation reservation);

    Optional<Reservation> findReservationById(Long reservationId);

    void deleteReservation(Long reservationId);
}
