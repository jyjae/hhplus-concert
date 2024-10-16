package com.hhplus.concert.infra.persistence.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueTokenJpaRepository extends JpaRepository<QueueTokenJpaEntity, Long>, QueueTokenJpaCustomRepository {
}
