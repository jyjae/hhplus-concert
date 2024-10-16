package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatRepository;
import com.hhplus.concert.domain.concert.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<ConcertDateSeat> findAvailableConcertDateSeat(Long concertDateId, Long concertDateSeatId) {
        return concertDateSeatJpaRepository
                .findAvailableConcertDateSeat(concertDateId, concertDateSeatId).map(
                        ConcertDateSeatJpaEntity::toDomain);

    }

}
