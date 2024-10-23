package com.hhplus.concert.domain.user.point.service;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.user.point.dto.UsePointCommand;
import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.GetPointQuery;
import com.hhplus.concert.domain.user.point.model.Point;
import com.hhplus.concert.domain.user.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointService {

    private final PointRepository pointRepository;
    private final TimeProvider provider;

    @Transactional
    public Long charge(ChargePointCommand command) {
        Point point = pointRepository.findPoint(command.getUserId())
                .orElse(Point.of(command.getUserId(),0, provider.getCurrentTimestamp()));

        return pointRepository.save(point.charge(command.getPoint()));
    }

    @Transactional
    public void use(UsePointCommand command) {
        Point point = pointRepository.findPoint(command.getUserId())
                .orElse(Point.of(command.getUserId(),0, provider.getCurrentTimestamp()));

        pointRepository.save(point.use(command.getPoint()));
    }

    @Transactional(readOnly = true)
    public int point(GetPointQuery query) {
        Optional<Point> point = pointRepository.findPoint(query.getUserId());
        return point.map(Point::getPoint).orElse(0);
    }
}
