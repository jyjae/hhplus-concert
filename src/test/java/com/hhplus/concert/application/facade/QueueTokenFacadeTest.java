package com.hhplus.concert.application.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueueTokenFacadeTest {

    @Autowired
    private QueueTokenFacade queueTokenFacade;

    @DisplayName("토큰 생성 테스트 성공")
    @Test
    void shouldCreateTokenSuccessfully() {
        // given
        Long userId = 1L;

        // when
        String token = queueTokenFacade.token(userId);

        // then
        assertThat(token).isNotNull();
    }
}