package com.hhplus.concert.application.facade;

import com.hhplus.concert.config.TestContainerConfig;
import com.hhplus.concert.domain.concert.model.Concert;
import com.hhplus.concert.domain.concert.model.ConcertDate;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ConcertFacadeTest {

    @Autowired
    private ConcertFacade concertFacade;
    @Autowired
    private QueueTokenService queueTokenService;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("콘서트 조회 테스트 성공")
    @Test
    void shouldRetrieveConcertSuccessfully() {
        // given

        // when
        List<Concert> concerts = concertFacade.concerts();

        // then
        assertThat(concerts).hasSize(2);
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 날짜 조회 파사드 통합 테스트 성공")
    @Test
    void shouldRetrieveAvailableDatesSuccessfullyInFacadeIntegrationTest() {
        // given

        // when
        List<ConcertDate> concertDates = concertFacade.concertDates(1L);

        // then
        assertThat(concertDates).hasSize(2);
        assertThat(concertDates.get(0).getId()).isEqualTo(1L);
        assertThat(concertDates.get(0).getPlace()).isEqualTo("Main Hall");
    }


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 좌석 조회 파사드 통합 테스트 성공")
    @Test
    void shouldRetrieveAvailableSeatsSuccessfullyInFacadeIntegrationTest() {
        // given

        // when
        List<ConcertDateSeat> concertDateSeats = concertFacade.concertDateSeats( 1L);

        // then
        assertThat(concertDateSeats).hasSize(25);
    }


}