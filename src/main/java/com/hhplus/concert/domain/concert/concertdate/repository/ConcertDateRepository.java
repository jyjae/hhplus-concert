package com.hhplus.concert.domain.concert.concertdate.repository;

import com.hhplus.concert.domain.concert.concertdate.model.ConcertDate;
import java.util.List;

public interface ConcertDateRepository {

  List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId);
}
