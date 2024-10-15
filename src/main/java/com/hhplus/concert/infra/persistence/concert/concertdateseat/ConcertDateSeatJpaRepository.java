package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertDateSeatJpaRepository extends JpaRepository<ConcertDateSeatJpaEntity, Long>, ConcertDateSeatJpaCustomRepository {

}
