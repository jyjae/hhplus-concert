package com.hhplus.concert.domain.user.point.repository;

import com.hhplus.concert.domain.user.point.model.Point;
import java.util.Optional;

public interface PointRepository {
    Long save(Point point);

    Optional<Point> findPoint(Long userId);
}
