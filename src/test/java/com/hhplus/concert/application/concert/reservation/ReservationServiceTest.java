package com.hhplus.concert.application.concert.reservation;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.reservation.Reservation;
import com.hhplus.concert.domain.concert.reservation.ReservationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TimeProvider timeProvider;

    @DisplayName("좌석 예약 성공")
    @Test
    void reserveConcertDateSeat() {
        // Given
        ReservationCommand command = new ReservationCommand(1L, 1L, 1000);
        // When
        when(reservationRepository.findReservation(1L)).thenReturn(Optional.empty());
        when(timeProvider.getCurrentTimestamp()).thenReturn(20220101L);
        when(timeProvider.getCurrentInstantPlusMinutes(5)).thenReturn(20520101L);
        Long reservationId = reservationService.reserveConcertDateSeat(command);

        // Then
        assertThat(reservationId).isNotNull();
    }

}