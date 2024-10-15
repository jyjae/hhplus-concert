package com.hhplus.concert.application.point;

import com.hhplus.concert.application.user.point.ChargePointCommand;
import com.hhplus.concert.application.user.point.GetPointQuery;
import com.hhplus.concert.application.user.point.PointService;
import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.user.point.Point;
import com.hhplus.concert.domain.user.point.PointRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private PointRepository pointRepository;

    @Mock
    private TimeProvider provider;

    @DisplayName("포인트 충전 테스트 성공")
    @Test
    void chargeSuccess() {
        // given
        ChargePointCommand command = new ChargePointCommand(1L, 10000);

        // when
        when(provider.getCurrentTimestamp()).thenReturn(20220101L);
        when(pointRepository.save(any(Point.class))).thenReturn(1L);
        when(pointRepository.findPoint(1L)).thenReturn(Optional.of(Point.of(1L, 1L, 10000, 20220101L)));
        Long id = pointService.charge(command);

        // then
        int point = pointService.point(new GetPointQuery(1L));
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1L);
        assertThat(point).isEqualTo(10000);
    }

}