package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.NotFoundException;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import com.hhplus.concert.util.UuidUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PointFacadeTest {

    @Autowired
    private PointFacade pointFacade;

    @Autowired
    private QueueTokenService queueTokenService;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("포인트 충전 파사드 통합 테스트 성공")
    @Test
    void shouldChargePointsSuccessfullyInFacadeIntegrationTest() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // when
        Long charge = pointFacade.charge(1L, new ChargePointRequest(1000));

        // then
        int point = pointFacade.point( 1L);
        assertThat(charge).isNotNull();
        assertThat(point).isEqualTo(1201000);
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("포인트 조회 파사드 통합 테스트 성공")
    @Test
    void shouldRetrievePointsSuccessfullyInFacadeIntegrationTest() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        pointFacade.charge(1L, new ChargePointRequest(1000));

        // when
        int point = pointFacade.point( 1L);

        // then
        assertThat(point).isEqualTo(1201000);
    }


}