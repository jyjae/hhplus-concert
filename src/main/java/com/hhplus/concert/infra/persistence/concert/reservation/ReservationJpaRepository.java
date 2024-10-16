package com.hhplus.concert.infra.persistence.concert.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, Long> {
    Optional<ReservationJpaEntity> findByConcertDateSeatId(Long concertDateSeatId);

    Optional<ReservationJpaEntity> findById(Long reservationId);
}