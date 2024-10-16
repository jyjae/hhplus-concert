package com.hhplus.concert.application.concert.reservation;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.reservation.Reservation;
import com.hhplus.concert.domain.concert.reservation.ReservationRepository;
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
    void reserveConcertDateSeatSuccess() {
        // Given
        ReservationCommand command = new ReservationCommand(1L, 1L, 1000);
        // When
        when(reservationRepository.findReservationExpiredDateAfter(1L, 20220101L)).thenReturn(Optional.empty());
        when(timeProvider.getCurrentTimestamp()).thenReturn(20220101L);
        when(timeProvider.getCurrentInstantPlusMinutes(5)).thenReturn(20520101L);
        Long reservationId = reservationService.reserveConcertDateSeat(command);

        // Then
        when(reservationRepository.findReservationExpiredDateAfter(reservationId, 20220101L)).thenReturn(Optional.of(new Reservation(1L, 10000, 1L, 20220101L, 20520101L)));
        Reservation reservation = reservationRepository.findReservationExpiredDateAfter(reservationId, 20220101L).get();
        assertThat(reservationId).isNotNull();
        assertThat(reservation.getUserId()).isEqualTo(1L);
        assertThat(reservation.getConcertDateSeatId()).isEqualTo(1L);

    }

    @DisplayName("이미 예약되어 있는 좌석 예약 실패")
    @Test
    void reserveConcertDateSeatFail() {
        // Given
        ReservationCommand command = new ReservationCommand(1L, 1L, 1000);
        // When
        when(timeProvider.getCurrentTimestamp()).thenReturn(20120101L);
        when(reservationRepository.findReservationExpiredDateAfter(1L, 20120101L)).thenReturn(Optional.of(new Reservation(1L, 10000, 1L, 20220101L, 20520101L)));

        // Then
        assertThatThrownBy(() -> reservationService.reserveConcertDateSeat(command))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Seat already reserved");

    }


}