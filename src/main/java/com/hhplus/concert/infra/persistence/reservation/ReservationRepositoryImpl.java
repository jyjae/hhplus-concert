package com.hhplus.concert.infra.persistence.reservation;

import com.hhplus.concert.domain.reservation.model.Reservation;
import com.hhplus.concert.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Optional<Reservation> findReservationExpiredDateAfter(Long reservationId, Long currentTime) {
        return reservationJpaRepository.findByIdAndExpirationDateAfter(reservationId, currentTime)
                .map(ReservationJpaEntity::toDomain);
    }

    @Override
    public Long reserveConcertDateSeat(Reservation reservation) {
        return reservationJpaRepository.save(ReservationJpaEntity.from(reservation)).getId();
    }

    @Override
    public Optional<Reservation> findReservationById(Long reservationId) {
        return reservationJpaRepository.findById(reservationId)
                .map(ReservationJpaEntity::toDomain);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        reservationJpaRepository.deleteById(reservationId);
    }
}
