package com.hhplus.concert.infra.persistence.concert;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertJpaRepository extends JpaRepository<ConcertJpaEntity, Long> {

}
