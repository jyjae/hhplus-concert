package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.concertdateseat.ConcertDateSeatService;
import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConcertDateSeatFacadeTest {
    @Autowired
    private ConcertDateSeatFacade concertDateSeatFacade;

    @Autowired
    private QueueTokenService queueTokenService;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 좌석 조회 파사드 통합 테스트 성공")
    @Test
    void getConcertDateSeatsSuccess() {
        // given
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();

        // when
        List<ConcertDateSeat> concertDateSeats = concertDateSeatFacade.concertDateSeats(token, 1L);

        // then
        assertThat(concertDateSeats).hasSize(25);
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("유효하지 않은 토큰으로 예약 가능한 좌석 조회 시 파사드 통합 테스트 실패")
    @Test
    void getConcertDateSeatsFail() {

        assertThatThrownBy(() -> concertDateSeatFacade.concertDateSeats(UUID.randomUUID().toString(), 1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Token not found");
    }
}