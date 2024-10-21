package com.hhplus.concert.infra.persistence.user.point;

import com.hhplus.concert.domain.user.point.model.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table
@Entity(name = "point")
public class PointJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "point")
    private int point;

    @Column(name = "last_updated")
    private Long lastUpdated;

    public static PointJpaEntity from(Point point) {
        return new PointJpaEntity(point.getId(), point.getUserId(), point.getPoint(), point.getLastUpdated());
    }

    public Point toDomain() {
        return Point.of(id, userId, point, lastUpdated);
    }
}
