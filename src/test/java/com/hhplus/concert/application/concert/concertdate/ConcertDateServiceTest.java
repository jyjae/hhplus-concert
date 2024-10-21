package com.hhplus.concert.application.concert.concertdate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.hhplus.concert.domain.concert.model.ConcertDate;
import com.hhplus.concert.domain.concert.repository.ConcertDateRepository;
import com.hhplus.concert.domain.concert.service.ConcertDateService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConcertDateServiceTest {

  @InjectMocks
  private ConcertDateService concertDateService;

  @Mock
  private ConcertDateRepository concertDateRepository;

  @DisplayName("최대 수용량보다 작은 현재 수용량 콘서트 날짜 조회 성공")
  @Test
  void shouldRetrieveConcertDatesWithCurrentCapacityLessThanMaxCapacity() {
    // Given
    List<ConcertDate> concertDates = List.of(
        ConcertDate.of(1L, 1L, 100, 50, 20220101L, "서울 송파구 올림픽공원 체조경기장"),
        ConcertDate.of(2L, 1L, 100, 100, 20220102L, "서울 송파구 올림픽공원 체조경기장"),
        ConcertDate.of(3L, 1L, 100, 50, 20220103L, "서울 송파구 잠실경기장"),
        ConcertDate.of(4L, 1L, 100, 50, 20220104L, "서울 송파구 잠실경기장")
    );
    when(concertDateRepository.getConcertDatesLessThanMaxCapacity(1L)).thenReturn(concertDates);

    // When
    List<ConcertDate> savedConcertDates = concertDateService.getConcertDatesLessThanMaxCapacity(1L);

    // Then
    assertThat(savedConcertDates).hasSize(3);
    assertThat(savedConcertDates.get(0).getConcertId()).isEqualTo(1L);
    assertThat(savedConcertDates.get(0).getTotalCapacity()).isGreaterThan(savedConcertDates.get(0).getCurrentCapacity());
  }



}