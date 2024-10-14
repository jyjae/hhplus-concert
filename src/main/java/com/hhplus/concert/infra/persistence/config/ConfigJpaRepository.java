package com.hhplus.concert.infra.persistence.config;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfigJpaRepository extends JpaRepository<ConfigJpaEntity, Long> {
    ConfigJpaEntity findByKey(String key);
}
