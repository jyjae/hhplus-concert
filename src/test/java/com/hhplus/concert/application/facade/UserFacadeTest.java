package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.config.ConfigKey;
import com.hhplus.concert.application.config.ConfigService;
import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.application.user.point.ChargePointCommand;
import com.hhplus.concert.application.user.point.PointService;
import com.hhplus.concert.infra.persistence.config.ConfigJpaEntity;
import com.hhplus.concert.infra.persistence.config.ConfigJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private QueueTokenService queueTokenService;

    @Autowired
    private ConfigJpaRepository configJpaRepository;

    @Autowired
    private PointService pointService;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("대기열 순서 조회")
    @Test
    void getRankSuccess() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        configJpaRepository.save(new ConfigJpaEntity(ConfigKey.MAX_PROCESSING_TOKEN.getKey(), "100", "대기열"));
        // when
        Long rank = userFacade.rank(1L, token);

        // then
        assertThat(rank).isEqualTo(0);
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("포인트 조회 성공")
    @Test
    void getPointSuccess() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        pointService.charge(new ChargePointCommand(1L, 1000));
        // when
        int point = userFacade.point(1L, token);

        // then
        assertThat(point).isEqualTo(1000);
    }

}