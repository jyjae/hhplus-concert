package com.hhplus.concert.application.token;

import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FindQueueTokenQueryTest {

    @DisplayName("token이 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenTokenIsNull() {
        // Given
        String token = null;

        // When & Then
        assertThatThrownBy(() -> new FindQueueTokenQuery(token))
                .isInstanceOf(InvalidException.class)
                .hasMessage("token is null");
    }
}