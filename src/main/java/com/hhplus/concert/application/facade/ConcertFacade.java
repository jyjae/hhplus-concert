package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.service.ConcertService;
import com.hhplus.concert.domain.concert.model.Concert;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertFacade {

  private final ConcertService concertService;


  public List<Concert> concerts() {
    return concertService.concerts();
  }

}
