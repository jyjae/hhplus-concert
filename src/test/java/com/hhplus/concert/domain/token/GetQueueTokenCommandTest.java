package com.hhplus.concert.domain.token;

import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GetQueueTokenCommandTest {

    @DisplayName("userId가 null일 경우 IllegalArgumentException이 발생")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenUserIdIsNull() {
        // Given
        Long userId = null;

        // When & Then
        assertThatThrownBy(() -> new GetQueueTokenCommand(userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userId is null");
    }

}