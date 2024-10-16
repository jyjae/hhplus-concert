package com.hhplus.concert.infra.persistence.concert.reservation;

import com.hhplus.concert.domain.concert.reservation.Reservation;
import com.hhplus.concert.domain.concert.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Optional<Reservation> findReservation(Long reservationId) {
        return reservationJpaRepository.findByConcertDateSeatId(reservationId)
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
}
