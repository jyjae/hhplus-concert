package com.hhplus.concert.infra.persistence.config;

import com.hhplus.concert.domain.config.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepository {

    private final ConfigJpaRepository configJpaRepository;


    @Override
    public String findByKeyValue(String key) {
        return configJpaRepository.findByKey(key).getValue();
    }

    @Override
    public void save(ConfigJpaEntity config) {
        configJpaRepository.save(config);
    }
}
