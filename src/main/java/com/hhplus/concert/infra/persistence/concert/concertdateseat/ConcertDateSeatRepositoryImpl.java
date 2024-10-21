package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.domain.concert.concertdateseat.model.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.repository.ConcertDateSeatRepository;
import com.hhplus.concert.domain.concert.concertdateseat.model.ConcertDateSeatStatus;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Optional<ConcertDateSeat> concertDateSeatById(Long concertDateSeatId) {
        return concertDateSeatJpaRepository.findByIdAndStatus(concertDateSeatId, ConcertDateSeatStatus.AVAILABLE.getStatus()).map(ConcertDateSeatJpaEntity::toDomain);
    }

    @Override
    public void save(ConcertDateSeat concertDateSeat) {
        ConcertDateSeatJpaEntity entity = concertDateSeatJpaRepository.findById(concertDateSeat.getId()).get();
        entity.update(concertDateSeat);
        concertDateSeatJpaRepository.save(entity);
    }

}
