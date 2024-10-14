package com.hhplus.concert.infra.persistence.token;


//import com.querydsl.jpa.impl.JPAQueryFactory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QueueTokenJpaCustomRepositoryImpl implements QueueTokenJpaCustomRepository {
    private final JPAQueryFactory query;

    QQueueTokenJpaEntity QueueTokenJpaEntity = QQueueTokenJpaEntity.queueTokenJpaEntity;


    @Override
    public QueueTokenJpaEntity findTokenByUserId(Long userId) {
        return query.select(QueueTokenJpaEntity)
                .from(QueueTokenJpaEntity)
                .where(QueueTokenJpaEntity.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public Optional<QueueTokenJpaEntity> findTokenByUserIdAndToken(Long userId, String token) {
        return Optional.ofNullable(query.select(QueueTokenJpaEntity)
                .from(QueueTokenJpaEntity)
                .where(QueueTokenJpaEntity.userId.eq(userId)
                        .and(QueueTokenJpaEntity.token.eq(token)))
                .fetchOne());
    }

    @Override
    public Long findLastProcessingToken(String queueTokenStatus) {
        return query.select(QueueTokenJpaEntity.id.max())
                .from(QueueTokenJpaEntity)
                .where(QueueTokenJpaEntity.status.eq(queueTokenStatus))
                .fetchOne();
    }

    @Override
    public List<QueueTokenJpaEntity> findAllTokenAsc(int count) {
        return query.select(QueueTokenJpaEntity)
                .from(QueueTokenJpaEntity)
                .orderBy(QueueTokenJpaEntity.id.asc())
                .limit(count)
                .fetch();
    }
}
