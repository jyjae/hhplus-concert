package com.hhplus.concert.infra.persistence.user.point;

import com.hhplus.concert.domain.user.point.model.Point;
import com.hhplus.concert.domain.user.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class PointRepositoryImpl implements PointRepository {

    private final PointJpaRepository pointJpaRepository;

    @Transactional
    @Override
    public Long save(Point point) {
        return pointJpaRepository.save(PointJpaEntity.from(point)).getId();
    }

    @Override
    public Optional<Point> findPoint(Long userId) {
        return pointJpaRepository.findByUserIdWithOptimisticLock(userId)
                    .map(PointJpaEntity::toDomain);

    }
}
