package com.hhplus.concert.application.concert.concertdate;

import com.hhplus.concert.domain.concert.concertdate.ConcertDate;
import com.hhplus.concert.domain.concert.concertdate.ConcertDateRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertDateService {

  private final ConcertDateRepository concertDateRepository;

  public List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId) {
    return concertDateRepository.getConcertDatesLessThanMaxCapacity(concertId)
        .stream()
        .filter(ConcertDate::isAvailable)
        .toList();
  }
}
