package com.hhplus.concert.application.token;

import com.hhplus.concert.domain.token.dto.GetUserQueueRankQuery;
import com.hhplus.concert.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GetUserQueueRankQueryTest {

    @DisplayName("tokenId가 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenTokenIdIsNull() {
        // Given
        Long tokenId = null;
        Integer capacity = 100;

        // When & Then
        assertThatThrownBy(() -> new GetUserQueueRankQuery(tokenId, capacity))
                .isInstanceOf(InvalidException.class)
                .hasMessage("tokenId is null");
    }

    @DisplayName("capacity가 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenCapacityIsNull() {
        // Given
        Long tokenId = 1L;
        Integer capacity = null;

        // When & Then
        assertThatThrownBy(() -> new GetUserQueueRankQuery(tokenId, capacity))
                .isInstanceOf(InvalidException.class)
                .hasMessage("capacity is null");
    }
}