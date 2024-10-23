package com.hhplus.concert.domain.concert.reservation;

import com.hhplus.concert.domain.reservation.dto.ReservationCommand;
import com.hhplus.concert.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationCommandTest {

    @DisplayName("userId가 null일 경우 IllegalArgumentException이 발생")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenUserIdIsNull() {
        // Given
        Long userId = null;
        Long concertDateSeatId = 1L;

        // When & Then
        assertThatThrownBy(() -> new ReservationCommand(userId, concertDateSeatId, 1000))
                .isInstanceOf(InvalidException.class)
                .hasMessage("userId is null");
    }

    @DisplayName("concertDateSeatId가 null일 경우 IllegalArgumentException이 발생")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConcertDateSeatIdIsNull() {
        // Given
        Long userId = 1L;
        Long concertDateSeatId = null;

        // When & Then
        assertThatThrownBy(() -> new ReservationCommand(userId, concertDateSeatId, 1000))
                .isInstanceOf(InvalidException.class)
                .hasMessage("concertDateSeatId is null");
    }

}