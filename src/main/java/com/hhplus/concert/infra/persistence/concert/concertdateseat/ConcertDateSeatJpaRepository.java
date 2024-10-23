package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ConcertDateSeatJpaRepository extends JpaRepository<ConcertDateSeatJpaEntity, Long>, ConcertDateSeatJpaCustomRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ConcertDateSeatJpaEntity> findByIdAndStatus(Long concertDateSeatId, String status);
}
