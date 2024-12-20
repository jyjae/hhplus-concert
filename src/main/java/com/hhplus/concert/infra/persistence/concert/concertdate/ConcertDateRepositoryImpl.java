package com.hhplus.concert.infra.persistence.concert.concertdate;

import com.hhplus.concert.domain.concert.model.ConcertDate;
import com.hhplus.concert.domain.concert.repository.ConcertDateRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertDateRepositoryImpl implements ConcertDateRepository {

  private final ConcertDateJpaRepository concertDateJpaRepository;

  @Override
  public List<ConcertDate> getConcertDatesLessThanMaxCapacity(Long concertId) {
    return concertDateJpaRepository.getConcertDatesLessThanMaxCapacity(concertId).stream()
        .map(ConcertDateJpaEntity::toDomain)
        .collect(Collectors.toList());
  }
}
