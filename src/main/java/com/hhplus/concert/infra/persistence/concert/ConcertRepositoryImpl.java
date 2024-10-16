package com.hhplus.concert.infra.persistence.concert;

import com.hhplus.concert.domain.concert.Concert;
import com.hhplus.concert.domain.concert.ConcertRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConcertRepositoryImpl implements ConcertRepository {

  private final ConcertJpaRepository concertJpaRepository;

  @Override
  public List<Concert> concerts() {
    return concertJpaRepository.findAll().stream()
        .map(ConcertJpaEntity::toDomain)
        .toList();
  }
}
