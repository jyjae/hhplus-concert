package com.hhplus.concert.application.concert;

import com.hhplus.concert.domain.concert.Concert;
import com.hhplus.concert.domain.concert.ConcertRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertService {

  private final ConcertRepository concertRepository;

  public List<Concert> concerts() {
    return concertRepository.concerts();
  }
}
