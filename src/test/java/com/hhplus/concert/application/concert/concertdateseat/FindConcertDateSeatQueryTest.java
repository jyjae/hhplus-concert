package com.hhplus.concert.application.concert.concertdateseat;

import com.hhplus.concert.exception.InvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FindConcertDateSeatQueryTest {

    @DisplayName("concertDateId가 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenConcertDateIdIsNull() {
        // Given
        Long concertDateId = null;
        Long concertDateSeatId = 1L;

        // When & Then
        assertThatThrownBy(() -> new FindConcertDateSeatQuery(concertDateId, concertDateSeatId))
                .isInstanceOf(InvalidException.class)
                .hasMessage("concertDateId is null");
    }

    @DisplayName("concertDateSeatId가 null일 경우 InvalidException이 발생")
    @Test
    void shouldThrowInvalidExceptionWhenConcertDateSeatIdIsNull() {
        // Given
        Long concertDateId = 1L;
        Long concertDateSeatId = null;

        // When & Then
        assertThatThrownBy(() -> new FindConcertDateSeatQuery(concertDateId, concertDateSeatId))
                .isInstanceOf(InvalidException.class)
                .hasMessage("concertDateSeatId is null");
    }

}