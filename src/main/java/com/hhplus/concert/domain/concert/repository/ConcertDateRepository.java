package com.hhplus.concert.domain.concert.repository;

import com.hhplus.concert.domain.concert.model.ConcertDate;
import java.util.List;

public interface ConcertDateRepository {

  List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId);
}
