package com.hhplus.concert.infra.persistence.concert.concertdate;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcertDateJpaCustomRepositoryImpl implements ConcertDateJpaCustomRepository {

  private final JPAQueryFactory query;

  QConcertDateJpaEntity concertDateJpaEntity = QConcertDateJpaEntity.concertDateJpaEntity;


  @Override
  public List<ConcertDateJpaEntity> getConcertDatesLessThanMaxCapacity(Long concertId) {
    return query.select(concertDateJpaEntity)
        .from(concertDateJpaEntity)
        .where(concertDateJpaEntity.concertId.eq(concertId)
            .and(concertDateJpaEntity.currentCapacity.lt(concertDateJpaEntity.totalCapacity)))
        .fetch();
  }
}
