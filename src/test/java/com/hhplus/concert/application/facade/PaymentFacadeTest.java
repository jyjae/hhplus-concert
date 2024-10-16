package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.reservation.ReservationService;
import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.application.user.point.ChargePointCommand;
import com.hhplus.concert.application.user.point.PointService;
import com.hhplus.concert.exception.InvalidException;
import com.hhplus.concert.exception.NotFoundException;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentRequest;
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
class PaymentFacadeTest {

    @Autowired
    private PaymentFacade paymentFacade;

    @Autowired
    private QueueTokenService queueTokenService;

    @Autowired
    private PointService pointService;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("포인트 부족으로 결제 파사드 통합 테스트 실패")
    @Test
    void paymentFailByNotEnoughPoint() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // when

        // then
         assertThatThrownBy(() -> paymentFacade.payment(token, new CreatePaymentRequest(1L, 1L)))
                .isInstanceOf(InvalidException.class)
                .hasMessage("Not enough point");
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("결제 파사드 통합 테스트 성공")
    @Test
    void paymentSuccess() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        pointService.charge(new ChargePointCommand(1L, 100000));

        // when
        Long paymentId = paymentFacade.payment(token, new CreatePaymentRequest(1L, 1L));

        // then
        assertThat(paymentId).isNotNull();
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약이 존재하지 않아 결제 파사드 통합 테스트 실패")
    @Test
    void paymentFailByNotFoundReservation() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // then
        assertThatThrownBy(() -> paymentFacade.payment(token, new CreatePaymentRequest(1L, 30L)))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("no reservation information");

    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("임시 예약이 풀려 결제 파사드 통합 테스트 실패")
    @Test
    void paymentFailByNotTemporaryReservation() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // then
        assertThatThrownBy(() -> paymentFacade.payment(token, new CreatePaymentRequest(1L, 2L)))
                .isInstanceOf(InvalidException.class)
                .hasMessage("reservation has expired");
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("토큰이 존재하지 않아 결제 파사드 통합 테스트 실패")
    @Test
    void paymentFailByNotFoundToken() {
        // given

        // then
        assertThatThrownBy(() -> paymentFacade.payment(UuidUtil.generateUuid(), new CreatePaymentRequest(1L, 1L)))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Token not found");
    }
}