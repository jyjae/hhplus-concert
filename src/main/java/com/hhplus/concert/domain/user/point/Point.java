package com.hhplus.concert.domain.user.point;

import com.hhplus.concert.exception.InvalidException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Point {
    private final Long id;
    private final Long userId;
    private final int point;
    private final Long lastUpdated;

    public static Point of(Long id, Long userId, int point, Long lastUpdated) {
        return Point.builder()
                .id(id)
                .userId(userId)
                .point(point)
                .lastUpdated(lastUpdated)
                .build();
    }

    public static Point of(Long userId, int point, Long lastUpdated) {
        return Point.builder()
                .userId(userId)
                .point(point)
                .lastUpdated(lastUpdated)
                .build();
    }

    public Point charge(int point) {
        return new Point(id, userId, this.point + point, lastUpdated);
    }

    public Point use(int point) {
        if(this.point < point) {
            throw new InvalidException("Not enough point");
        }
        return new Point(id, userId, this.point - point, lastUpdated);
    }
}
