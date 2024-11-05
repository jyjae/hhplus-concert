package com.hhplus.concert;

import com.hhplus.concert.domain.config.model.ConfigKey;
import com.hhplus.concert.common.TimeProvider;
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
@Profile("dev")
public class DataInitializer implements ApplicationRunner {

    private final UserJpaRepository userJpaRepository;
    private final ConfigRepository configRepository;
    private final ConcertJpaRepository concertJpaRepository;
    private final ConcertDateJpaRepository concertDateJpaRepository;
    private final ConcertDateSeatJpaRepository concertDateSeatJpaRepository;
    private final QueueTokenJpaRepository queueTokenJpaRepository;
    private final TimeProvider timeProvider;

    public DataInitializer(
            UserJpaRepository userJpaRepository,
            ConfigRepository configRepository,
            ConcertJpaRepository concertJpaRepository,
            ConcertDateJpaRepository concertDateJpaRepository,
            ConcertDateSeatJpaRepository concertDateSeatJpaRepository, QueueTokenJpaRepository queueTokenJpaRepository, TimeProvider timeProvider
    ) {
        this.userJpaRepository = userJpaRepository;
        this.configRepository = configRepository;
        this.concertJpaRepository = concertJpaRepository;
        this.concertDateJpaRepository = concertDateJpaRepository;
        this.concertDateSeatJpaRepository = concertDateSeatJpaRepository;
        this.queueTokenJpaRepository = queueTokenJpaRepository;
        this.timeProvider = timeProvider;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserJpaEntity user1 = UserJpaEntity.of("John Doe");
        UserJpaEntity user2 = UserJpaEntity.of("Jane Doe");
        UserJpaEntity user3 = UserJpaEntity.of("Alice Smith");

        // 엔티티 저장
        userJpaRepository.save(user1);
        userJpaRepository.save(user2);
        userJpaRepository.save(user3);

        QueueTokenJpaEntity queueToken = QueueTokenJpaEntity.of(1L, UuidUtil.generateUuid(), QueueTokenStatus.WAITING.getValue(), timeProvider.getCurrentInstantPlusMinutes(30));
        queueTokenJpaRepository.save(queueToken);
//
//        ConfigJpaEntity config1 = new ConfigJpaEntity(ConfigKey.MAX_PROCESSING_TOKEN.getKey(), "10", "10");
//        configRepository.save(config1);

        for (int i = 1; i <= 10000; i++) {
            String name = "Concert " + i;
            long startDate = 20240101L + i;  // 시작 날짜를 각 콘서트마다 다르게 설정
            long endDate = startDate + 4;     // 종료 날짜는 시작 날짜 +4일

            ConcertJpaEntity concert = ConcertJpaEntity.of(name, startDate, endDate);
            concertJpaRepository.save(concert);
        }
        ConcertDateJpaEntity concertDate1 = ConcertDateJpaEntity.of(1L, 50, 10, "Seoul", 20240101L);
        ConcertDateJpaEntity concertDate2 = ConcertDateJpaEntity.of(2L, 50, 20, "Busan", 20240201L);

        concertDateJpaRepository.save(concertDate1);
        concertDateJpaRepository.save(concertDate2);

        List<ConcertDateSeatJpaEntity> concertDateSeats = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            ConcertDateSeatJpaEntity concertDateSeat = ConcertDateSeatJpaEntity.of(
                    (long) 1,                // concertDateId를 1부터 50까지
                    10000 + (i * 500),       // 가격을 10000부터 시작해 i에 따라 증가
                    System.currentTimeMillis() + timeProvider.getCurrentInstantPlusMinutes(30),           // 만료일을 20240101부터 i만큼 더해 설정
                    i % 2 == 0 ? "AVAILABLE" : "RESERVED"  // 짝수는 AVAILABLE, 홀수는 RESERVED
            );

            ConcertDateSeatJpaEntity concertDateSeat2 = ConcertDateSeatJpaEntity.of(
                    (long) 2,                // concertDateId를 1부터 50까지
                    10000 + (i * 500),       // 가격을 10000부터 시작해 i에 따라 증가
                    System.currentTimeMillis() + timeProvider.getCurrentInstantPlusMinutes(30),           // 만료일을 20240101부터 i만큼 더해 설정
                    i % 2 == 0 ? "AVAILABLE" : "RESERVED"  // 짝수는 AVAILABLE, 홀수는 RESERVED
            );

            concertDateSeats.add(concertDateSeat);
            concertDateSeats.add(concertDateSeat2);
        }

        concertDateSeatJpaRepository.saveAll(concertDateSeats);

    }
}
