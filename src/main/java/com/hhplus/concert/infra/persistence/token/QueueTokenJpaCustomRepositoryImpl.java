package com.hhplus.concert.infra.persistence.token;


//import com.querydsl.jpa.impl.JPAQueryFactory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class QueueTokenJpaCustomRepositoryImpl implements QueueTokenJpaCustomRepository {
    private final JPAQueryFactory query;

    QQueueTokenJpaEntity queueTokenJpaEntity = QQueueTokenJpaEntity.queueTokenJpaEntity;


    @Override
    public QueueTokenJpaEntity findTokenByUserId(Long userId) {
        return query.select(queueTokenJpaEntity)
                .from(queueTokenJpaEntity)
                .where(queueTokenJpaEntity.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public Optional<QueueTokenJpaEntity> findTokenByToken(String token, Long currentTime) {
        return Optional.ofNullable(query.select(queueTokenJpaEntity)
                .from(queueTokenJpaEntity)
                .where(queueTokenJpaEntity.token.eq(token)
                        .and(queueTokenJpaEntity.expiredDate.gt(currentTime)))
                .fetchOne());
    }

    @Override
    public Long findLastProcessingToken(String queueTokenStatus) {
        Long result = query.select(queueTokenJpaEntity.id.max())
                .from(queueTokenJpaEntity)
                .where(queueTokenJpaEntity.status.eq(queueTokenStatus))
                .fetchOne();

        return Optional.ofNullable(result).orElse(0L);
    }

    @Override
    public List<QueueTokenJpaEntity> findAllTokenAsc(int count) {
        return query.select(queueTokenJpaEntity)
                .from(queueTokenJpaEntity)
                .orderBy(queueTokenJpaEntity.id.asc())
                .limit(count)
                .fetch();
    }
}
