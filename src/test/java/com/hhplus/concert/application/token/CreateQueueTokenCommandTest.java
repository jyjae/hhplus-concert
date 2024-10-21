package com.hhplus.concert.application.token;

import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateQueueTokenCommandTest {

    @DisplayName("userId가 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenUserIdIsNull() {
        // Given
        Long userId = null;
        String token = "validToken";

        // When & Then
        assertThatThrownBy(() -> new CreateQueueTokenCommand(userId, token))
                .isInstanceOf(InvalidException.class)
                .hasMessage("userId is null");
    }

    @DisplayName("token이 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenTokenIsNull() {
        // Given
        Long userId = 1L;
        String token = null;

        // When & Then
        assertThatThrownBy(() -> new CreateQueueTokenCommand(userId, token))
                .isInstanceOf(InvalidException.class)
                .hasMessage("token is null");
    }
}