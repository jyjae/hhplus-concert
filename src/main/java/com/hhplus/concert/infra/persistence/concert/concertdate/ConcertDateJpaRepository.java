package com.hhplus.concert.infra.persistence.concert.concertdate;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDateJpaRepository extends JpaRepository<ConcertDateJpaEntity, Long>, ConcertDateJpaCustomRepository {

}
