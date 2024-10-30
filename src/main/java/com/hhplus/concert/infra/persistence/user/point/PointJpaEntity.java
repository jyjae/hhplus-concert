package com.hhplus.concert.infra.persistence.user.point;

import com.hhplus.concert.domain.user.point.model.Point;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    @Version
//    private Long version = 0L;

    public PointJpaEntity(Long id, Long userId, int point, Long lastUpdated) {
        this.id = id;
        this.userId = userId;
        this.point = point;
        this.lastUpdated = lastUpdated;
    }

    public static PointJpaEntity from(Point point) {
        return new PointJpaEntity(point.getId(), point.getUserId(), point.getPoint(), point.getLastUpdated());
    }

    public Point toDomain() {
        return Point.of(id, userId, point, lastUpdated);
    }
}
