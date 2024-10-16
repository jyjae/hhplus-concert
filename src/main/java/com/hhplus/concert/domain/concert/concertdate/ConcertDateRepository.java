package com.hhplus.concert.domain.concert.concertdate;

import com.hhplus.concert.domain.concert.Concert;
import java.util.Arrays;
import java.util.List;

public interface ConcertDateRepository {

  List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId);
}
