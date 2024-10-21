package com.hhplus.concert.application.concert;

import static org.mockito.Mockito.when;

import com.hhplus.concert.domain.concert.model.Concert;
import com.hhplus.concert.domain.concert.repository.ConcertRepository;
import com.hhplus.concert.domain.concert.service.ConcertService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

  @InjectMocks
  private ConcertService concertService;

  @Mock
  private ConcertRepository concertRepository;

  //  콘서트 목록 조회 테스트 성공
  @DisplayName("콘서트 목록 조회 테스트 성공")
  @Test
  void shouldRetrieveConcertListSuccessfully() {
    // Given
    List<Concert> concerts = List.of(
        Concert.of(1L, "콘서트1", 20220101L, 20220102L),
        Concert.of(2L, "콘서트2", 20220103L, 20220104L)
    );
    when(concertRepository.concerts()).thenReturn(concerts);

    // When
    List<Concert> savedConcerts = concertService.concerts();

    // Then
    assertThat(savedConcerts).hasSize(2);
    assertThat(savedConcerts.get(0).getName()).isEqualTo("콘서트1");
    assertThat(savedConcerts.get(1).getName()).isEqualTo("콘서트2");
  }



}