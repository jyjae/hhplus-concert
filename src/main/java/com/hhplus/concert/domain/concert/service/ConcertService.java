package com.hhplus.concert.domain.concert.service;

import com.hhplus.concert.domain.concert.model.Concert;
import com.hhplus.concert.domain.concert.repository.ConcertRepository;
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
