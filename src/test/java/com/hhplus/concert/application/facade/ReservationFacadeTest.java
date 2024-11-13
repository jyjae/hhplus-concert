package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
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

@SpringBootTest
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private QueueTokenService queueTokenService;


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 파사드 통합 테스트 성공")
    @Test
    void shouldCompleteReservationSuccessfullyInFacadeIntegrationTest() {
        // given

        // when
        Long reservationId = reservationFacade.reservation(1L, 1L, 7L );

        // then
        assertThat(reservationId).isNotNull();
    }


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능하지 않은 좌석으로 예약 파사드 통합 테스트 실패")
    @Test
    void shouldFailReservationInFacadeIntegrationTestDueToUnavailableSeat() {
        // given

        // when

        // then
        assertThatThrownBy(() -> reservationFacade.reservation(1L, 1L, 2L ))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Seat already reserved");
    }


}