package com.hhplus.concert.domain.concert.concertdate.service;

import com.hhplus.concert.domain.concert.concertdate.model.ConcertDate;
import com.hhplus.concert.domain.concert.concertdate.repository.ConcertDateRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ConcertDateService {

  private final ConcertDateRepository concertDateRepository;

  @Transactional(readOnly = true)
  public List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId) {
    return concertDateRepository.getConcertDatesLessThanMaxCapacity(concertId)
        .stream()
        .filter(ConcertDate::isAvailable)
        .toList();
  }
}
