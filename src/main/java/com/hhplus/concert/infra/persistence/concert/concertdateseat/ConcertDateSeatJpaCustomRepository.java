package com.hhplus.concert.infra.persistence.concert.concertdateseat;


import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;

import java.util.List;
import java.util.Optional;

public interface ConcertDateSeatJpaCustomRepository {

    List<ConcertDateSeatJpaEntity> concertDateSeats(long concertDateId, long now);

    Optional<ConcertDateSeatJpaEntity> findAvailableConcertDateSeat(Long concertDateId, Long concertDateSeatId);
}
