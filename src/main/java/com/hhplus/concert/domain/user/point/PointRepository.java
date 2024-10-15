package com.hhplus.concert.domain.user.point;

import java.util.Optional;

public interface PointRepository {
    Long save(Point point);

    Optional<Point> findPoint(Long userId);
}
