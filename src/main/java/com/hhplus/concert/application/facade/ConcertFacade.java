package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.model.ConcertDate;
import com.hhplus.concert.domain.concert.service.ConcertDateService;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.domain.concert.service.ConcertService;
import com.hhplus.concert.domain.concert.model.Concert;
import java.util.List;

import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertFacade {

  private final ConcertService concertService;
  private final ConcertDateService concertDateService;
  private final ConcertDateSeatService concertDateSeatService;


  public List<Concert> concerts() {
    return concertService.concerts();
  }

  public List<ConcertDate> concertDates(Long concertId) {
    return concertDateService.getConcertDatesLessThanMaxCapacity(concertId);
  }

  public List<ConcertDateSeat> concertDateSeats(Long concertDateId) {
    return concertDateSeatService.getAvailableSeats(concertDateId);
  }

}
