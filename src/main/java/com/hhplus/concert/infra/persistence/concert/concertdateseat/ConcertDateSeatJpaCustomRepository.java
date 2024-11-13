package com.hhplus.concert.infra.persistence.concert.concertdateseat;


import java.util.List;
import java.util.Optional;

public interface ConcertDateSeatJpaCustomRepository {

    List<ConcertDateSeatJpaEntity> concertDateSeats(long concertDateId);

    Optional<ConcertDateSeatJpaEntity> findAvailableConcertDateSeat(Long concertDateId, Long concertDateSeatId);
}
