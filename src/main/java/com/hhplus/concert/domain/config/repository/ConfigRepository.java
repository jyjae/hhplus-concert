package com.hhplus.concert.domain.config.repository;


import com.hhplus.concert.infra.persistence.config.ConfigJpaEntity;

public interface ConfigRepository {
    String findByKeyValue(String key);

    void save(ConfigJpaEntity config1);
}
