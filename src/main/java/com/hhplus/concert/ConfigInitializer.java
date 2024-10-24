package com.hhplus.concert;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.config.model.ConfigKey;
import com.hhplus.concert.domain.config.repository.ConfigRepository;
import com.hhplus.concert.domain.token.model.QueueTokenStatus;
import com.hhplus.concert.infra.persistence.concert.ConcertJpaEntity;
import com.hhplus.concert.infra.persistence.concert.ConcertJpaRepository;
import com.hhplus.concert.infra.persistence.concert.concertdate.ConcertDateJpaEntity;
import com.hhplus.concert.infra.persistence.concert.concertdate.ConcertDateJpaRepository;
import com.hhplus.concert.infra.persistence.concert.concertdateseat.ConcertDateSeatJpaEntity;
import com.hhplus.concert.infra.persistence.concert.concertdateseat.ConcertDateSeatJpaRepository;
import com.hhplus.concert.infra.persistence.config.ConfigJpaEntity;
import com.hhplus.concert.infra.persistence.token.QueueTokenJpaEntity;
import com.hhplus.concert.infra.persistence.token.QueueTokenJpaRepository;
import com.hhplus.concert.infra.persistence.user.UserJpaEntity;
import com.hhplus.concert.infra.persistence.user.UserJpaRepository;
import com.hhplus.concert.util.UuidUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("test")
public class ConfigInitializer implements ApplicationRunner {
    private final ConfigRepository configRepository;

    public ConfigInitializer(
            ConfigRepository configRepository
    ) {
        this.configRepository = configRepository;
    }


    @Override
    public void run(ApplicationArguments args) {
        ConfigJpaEntity config1 = new ConfigJpaEntity(ConfigKey.MAX_PROCESSING_TOKEN.getKey(), "10", "10");
        configRepository.save(config1);
    }
}
