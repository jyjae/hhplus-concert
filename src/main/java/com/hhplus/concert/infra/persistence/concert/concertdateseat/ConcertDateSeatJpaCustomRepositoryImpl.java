package com.hhplus.concert.infra.persistence.concert.concertdateseat;


import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConcertDateSeatJpaCustomRepositoryImpl implements ConcertDateSeatJpaCustomRepository {

    private final JPAQueryFactory factory;
    QConcertDateSeatJpaEntity qConcertDateSeat = QConcertDateSeatJpaEntity.concertDateSeatJpaEntity;

    @Override
    public List<ConcertDateSeatJpaEntity> concertDateSeats(long concertDateId, long now) {
        return factory.select(qConcertDateSeat)
                .from(qConcertDateSeat)
                .where(qConcertDateSeat.concertDateId.eq(concertDateId)
                        .and(qConcertDateSeat.expiredDate.gt(now))
                        .and(qConcertDateSeat.status.ne(ConcertDateSeatStatus.RESERVED.name())))
                .fetch();
    }
}
