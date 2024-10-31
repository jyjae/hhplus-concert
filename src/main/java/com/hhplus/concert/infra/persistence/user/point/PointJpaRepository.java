package com.hhplus.concert.infra.persistence.user.point;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface PointJpaRepository extends JpaRepository<PointJpaEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<PointJpaEntity> findByUserId(Long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select p from point p where p.userId = :userId")
    Optional<PointJpaEntity> findByUserIdWithOptimisticLock(Long userId);
}
