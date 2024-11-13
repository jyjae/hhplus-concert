package com.hhplus.concert.infra.persistence.reservation;

import aj.org.objectweb.asm.commons.Remapper;
import com.hhplus.concert.domain.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, Long> {

    Optional<ReservationJpaEntity> findByConcertDateSeatId(Long concertDateSeatId);
}
