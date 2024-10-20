package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.concertdate.ConcertDateService;
import com.hhplus.concert.application.token.GetQueueTokenCommand;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdate.ConcertDate;
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

@SpringBootTest
class ConcertDateFacadeTest {

    @Autowired
    private ConcertDateFacade concertDateFacade;

    @Autowired
    private QueueTokenService queueTokenService;


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 날짜 조회 파사드 통합 테스트 성공")
    @Test
    void getConcertDatesSuccess() {
        // given
        QueueToken token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L));

        // when
        List<ConcertDate> concertDates = concertDateFacade.concertDates(1L, token.getToken());

        // then
        assertThat(concertDates).hasSize(2);
        assertThat(concertDates.get(0).getId()).isEqualTo(1L);
        assertThat(concertDates.get(0).getPlace()).isEqualTo("Main Hall");
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("유효하지 않은 토큰으로 예약 가능한 날짜 조회 파사드 통합 테스트 실패")
    @Test
    void getConcertDatesFail() {
        // given

        // when

        // then
        assertThatThrownBy(() -> concertDateFacade.concertDates(1L, UUID.randomUUID().toString()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Token not found");
    }

}