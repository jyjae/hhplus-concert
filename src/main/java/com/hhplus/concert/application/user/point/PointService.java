package com.hhplus.concert.application.user.point;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.user.point.Point;
import com.hhplus.concert.domain.user.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointService {

    private final PointRepository pointRepository;
    private final TimeProvider provider;

    public Long charge(ChargePointCommand command) {
        Point point = pointRepository.findPoint(command.getUserId())
                .orElse(Point.of(null, command.getUserId(),0, provider.getCurrentTimestamp()));

        return pointRepository.save(point.charge(command.getPoint()));
    }

    public void use(UsePointCommand command) {
        Point point = pointRepository.findPoint(command.getUserId())
                .orElse(Point.of(null, command.getUserId(),0, provider.getCurrentTimestamp()));

        pointRepository.save(point.use(command.getPoint()));
    }

    public int point(GetPointQuery query) {
        if(pointRepository.findPoint(query.getUserId()).isEmpty()) {
            return 0;
        }
        return pointRepository.findPoint(query.getUserId()).get().getPoint();
    }
}
