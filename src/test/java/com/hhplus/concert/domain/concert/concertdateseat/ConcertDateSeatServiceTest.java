package com.hhplus.concert.domain.concert.concertdateseat;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.constants.ConcertDateSeatStatus;
import com.hhplus.concert.domain.concert.dto.FindConcertDateSeatQuery;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.concert.repository.ConcertDateSeatRepository;
import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.exception.AlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConcertDateSeatServiceTest {

    @InjectMocks
    private ConcertDateSeatService concertDateSeatService;

    @Mock
    private ConcertDateSeatRepository concertDateSeatRepository;

    @Mock
    private TimeProvider timeProvider;

    //  예약 가능한 좌석 조회 성공
    @DisplayName("예약 가능한 좌석 조회 성공")
    @Test
    void shouldRetrieveAvailableSeatsSuccessfully() {
        // Given
        List<ConcertDateSeat> list = List.of(
                ConcertDateSeat.of(1L, 1L, 100, ConcertDateSeatStatus.AVAILABLE),
                ConcertDateSeat.of(1L, 2L, 100, ConcertDateSeatStatus.RESERVED),
                ConcertDateSeat.of(1L, 3L, 100, ConcertDateSeatStatus.AVAILABLE),
                ConcertDateSeat.of(1L, 4L, 100, ConcertDateSeatStatus.AVAILABLE)
        );

        when(timeProvider.getCurrentTimestamp()).thenReturn(20220100L);
        when(concertDateSeatRepository.concertDateSeat(1L, timeProvider.getCurrentTimestamp())).thenReturn(list);
        // When

        List<ConcertDateSeat> availableSeats = concertDateSeatService.getAvailableSeats(1L);

        // Then
        assertThat(availableSeats).hasSize(3);
        assertThat(availableSeats.get(0).getId()).isEqualTo(1L);
        assertThat(availableSeats.get(0).getStatus()).isEqualTo(ConcertDateSeatStatus.AVAILABLE);
    }

    @DisplayName("예약 가능한 좌석 조회 실패")
    @Test
    void getAvailableSeatsFail() {
        // Given
//        ConcertDateSeat concertDateSeat = ConcertDateSeat.of(1L, 1L, 100, 20220101L, ConcertDateSeatStatus.AVAILABLE);
        when(concertDateSeatRepository.findAvailableConcertDateSeat(1L, 1L))
                .thenReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> concertDateSeatService.findAvailableConcertDateSeat(new FindConcertDateSeatQuery(1L, 1L)))
                .isInstanceOf(AlreadyExistsException.class)
                .hasMessage("Seat already reserved");

    }



}