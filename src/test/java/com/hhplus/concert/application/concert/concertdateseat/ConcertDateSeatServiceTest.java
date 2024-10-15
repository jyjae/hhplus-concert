package com.hhplus.concert.application.concert.concertdateseat;

import static org.mockito.Mockito.when;

import java.util.List;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatRepository;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

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
    void getAvailableSeats() {
        // Given
        List<ConcertDateSeat> list = List.of(
                ConcertDateSeat.of(1L, 1L, 100, 20220101L, ConcertDateSeatStatus.AVAILABLE),
                ConcertDateSeat.of(1L, 2L, 100, 20220101L, ConcertDateSeatStatus.RESERVED),
                ConcertDateSeat.of(1L, 3L, 100, 20220101L, ConcertDateSeatStatus.AVAILABLE),
                ConcertDateSeat.of(1L, 4L, 100, 20220101L, ConcertDateSeatStatus.AVAILABLE)
        );

        when(timeProvider.getCurrentTimestamp()).thenReturn(20220100L);
        when(concertDateSeatRepository.concertDateSeat(1L, timeProvider.getCurrentTimestamp())).thenReturn(list);
        // When

        List<ConcertDateSeat> availableSeats = concertDateSeatService.getAvailableSeats(1L);

        // Then
        assertThat(availableSeats).hasSize(3);
    }




}