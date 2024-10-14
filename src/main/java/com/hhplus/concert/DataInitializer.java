package com.hhplus.concert;

import com.hhplus.concert.application.config.ConfigKey;
import com.hhplus.concert.domain.config.ConfigRepository;
import com.hhplus.concert.domain.user.UserRepository;
import com.hhplus.concert.infra.persistence.config.ConfigJpaEntity;
import com.hhplus.concert.infra.persistence.config.ConfigJpaRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ConfigRepository configRepository;

    public DataInitializer(UserRepository userRepository, ConfigRepository configRepository) {
        this.userRepository = userRepository;
        this.configRepository = configRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        UserJpaEntity user1 = UserJpaEntity.of(null, "John Doe", "john@example.com");
//        UserJpaEntity user2 = UserJpaEntity.of(null, "Jane Doe", "jane@example.com");
//        UserJpaEntity user3 = UserJpaEntity.of(null, "Alice Smith", "alice@example.com");
//
//        // 엔티티 저장
//        userJpaRepository.save(user1);
//        userJpaRepository.save(user2);
//        userJpaRepository.save(user3);
        ConfigJpaEntity config1 = new ConfigJpaEntity(ConfigKey.MAX_PROCESSING_TOKEN.getKey(), "10", "10");
        configRepository.save(config1);

    }
}
