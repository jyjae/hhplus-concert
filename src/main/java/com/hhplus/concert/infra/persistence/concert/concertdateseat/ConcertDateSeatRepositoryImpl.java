package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.common.TimeHolder;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConcertDateSeatRepositoryImpl implements ConcertDateSeatRepository {

    private final ConcertDateSeatJpaRepository concertDateSeatJpaRepository;

    @Override
    public List<ConcertDateSeat> concertDateSeat(long concertDateId, long currentTime) {
        return concertDateSeatJpaRepository.concertDateSeats(concertDateId, currentTime).stream()
                .map(ConcertDateSeatJpaEntity::toDomain)
                .toList();
    }
}
