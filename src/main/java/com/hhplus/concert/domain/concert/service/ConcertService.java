package com.hhplus.concert.domain.concert.service;

import com.hhplus.concert.domain.concert.dto.Concerts;
import com.hhplus.concert.domain.concert.model.Concert;
import com.hhplus.concert.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertService {

  private final ConcertRepository concertRepository;

  @Cacheable(cacheNames = "getConcerts", key = "'concerts:'")
  public Concerts concerts() {
    return new Concerts(concertRepository.concerts());
  }
}
