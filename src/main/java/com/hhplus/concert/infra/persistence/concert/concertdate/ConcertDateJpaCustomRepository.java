package com.hhplus.concert.infra.persistence.concert.concertdate;

import java.util.List;

public interface ConcertDateJpaCustomRepository {

  List<ConcertDateJpaEntity> getConcertDatesLessThanMaxCapacity(Long concertId);
}
