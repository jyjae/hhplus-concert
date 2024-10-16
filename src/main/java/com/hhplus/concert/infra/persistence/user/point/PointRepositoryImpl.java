package com.hhplus.concert.infra.persistence.user.point;

import com.hhplus.concert.domain.user.point.Point;
import com.hhplus.concert.domain.user.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PointRepositoryImpl implements PointRepository {

    private final PointJpaRepository pointJpaRepository;

    @Transactional
    @Override
    public Long save(Point point) {
        return pointJpaRepository.save(PointJpaEntity.from(point)).getId();
    }

    @Override
    public Optional<Point> findPoint(Long userId) {
        return pointJpaRepository.findByUserId(userId)
                .map(PointJpaEntity::toDomain);
    }
}
