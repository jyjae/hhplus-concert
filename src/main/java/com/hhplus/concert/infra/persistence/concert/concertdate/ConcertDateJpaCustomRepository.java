package com.hhplus.concert.infra.persistence.concert.concertdate;

import com.hhplus.concert.domain.concert.concertdate.ConcertDate;
import java.util.List;

public interface ConcertDateJpaCustomRepository {

  List<ConcertDateJpaEntity> getConcertDatesLessThanMaxCapacity(Long concertId);
}
