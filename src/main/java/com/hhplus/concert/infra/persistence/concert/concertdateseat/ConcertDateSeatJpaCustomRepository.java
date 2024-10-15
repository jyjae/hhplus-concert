package com.hhplus.concert.infra.persistence.concert.concertdateseat;


import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;

import java.util.List;

public interface ConcertDateSeatJpaCustomRepository {

    List<ConcertDateSeatJpaEntity> concertDateSeats(long concertDateId, long now);
}
