package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.Concert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConcertFacadeTest {

    @Autowired
    private ConcertFacade concertFacade;

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

}