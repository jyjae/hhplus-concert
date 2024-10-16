package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.NotFoundException;
import com.hhplus.concert.util.UuidUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private QueueTokenService queueTokenService;


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 파사드 통합 테스트 성공")
    @Test
    void reservationSuccess() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // when
        Long reservationId = reservationFacade.reservation(token, 1L, 1L, 5L );

        // then
        assertThat(reservationId).isNotNull();
    }


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("존재하지 않은 토큰으로 예약 파사드 통합 테스트 실패")
    @Test
    void reservationFail() {
        // given

        // when

        // then
        assertThatThrownBy(() -> reservationFacade.reservation(UuidUtil.generateUuid(), 1L, 1L, 1L ))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Token not found");
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능하지 않은 좌석으로 예약 파사드 통합 테스트 실패")
    @Test
    void reservationFailByNotAvailableSeat() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // when

        // then
        assertThatThrownBy(() -> reservationFacade.reservation(token, 1L, 1L, 2L ))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Seat already reserved");
    }


}