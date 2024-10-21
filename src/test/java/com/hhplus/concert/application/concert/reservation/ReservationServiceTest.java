package com.hhplus.concert.application.concert.reservation;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.reservation.model.Reservation;
import com.hhplus.concert.domain.reservation.dto.ReservationCommand;
import com.hhplus.concert.domain.reservation.repository.ReservationRepository;
import com.hhplus.concert.domain.reservation.service.ReservationService;
import com.hhplus.concert.exception.AlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void shouldReserveSeatSuccessfully() {
        // Given
        ReservationCommand command = new ReservationCommand(1L, 1L, 1000);
        // When
        when(reservationRepository.findReservationExpiredDateAfter(1L, 20220101L)).thenReturn(Optional.empty());
        when(timeProvider.getCurrentTimestamp()).thenReturn(20220101L);
        when(timeProvider.getCurrentInstantPlusMinutes(5)).thenReturn(20520101L);
        Long reservationId = reservationService.reserveConcertDateSeat(command);

        // Then
        when(reservationRepository.findReservationExpiredDateAfter(reservationId, 20220101L)).thenReturn(Optional.of(
                Reservation.builder()
                        .id(1L)
                        .concertDateSeatId(1L)
                        .userId(1L)
                        .price(10000)
                        .reservationDate(20220101L)
                        .expirationDate(20520101L)
                        .build()
        ));
        Reservation reservation = reservationRepository.findReservationExpiredDateAfter(reservationId, 20220101L).get();
        assertThat(reservationId).isNotNull();
        assertThat(reservation.getUserId()).isEqualTo(1L);
        assertThat(reservation.getConcertDateSeatId()).isEqualTo(1L);

    }

    @DisplayName("이미 예약되어 있는 좌석 예약 실패")
    @Test
    void shouldFailToReserveSeatWhenAlreadyReserved() {
        // Given
        ReservationCommand command = new ReservationCommand(1L, 1L, 1000);
        // When
        when(timeProvider.getCurrentTimestamp()).thenReturn(20120101L);
        when(reservationRepository.findReservationExpiredDateAfter(1L, 20120101L)).thenReturn(Optional.of(
                Reservation.builder()
                        .id(1L)
                        .userId(1L)
                        .price(10000)
                        .reservationDate(20120101L)
                        .expirationDate(20420101L)
                        .build()
        ));

        // Then
        assertThatThrownBy(() -> reservationService.reserveConcertDateSeat(command))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Seat already reserved");

    }


}