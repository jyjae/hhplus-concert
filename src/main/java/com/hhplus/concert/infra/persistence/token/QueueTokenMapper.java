package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.model.QueueTokenStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueueTokenMapper {

    public QueueToken mapToDomain(QueueTokenJpaEntity entity) {
        return QueueToken.of(entity.getId(), entity.getUserId(), entity.getToken(), entity.getCreatedAt(), QueueTokenStatus.valueOf(entity.getStatus()),
            entity.getExpiredDate());
    }

    public Optional<QueueToken> mapToDomainOptional(Optional<QueueTokenJpaEntity> entityOptional) {
        return entityOptional.map(this::mapToDomain);
    }


    public QueueTokenJpaEntity mapToEntity(QueueToken queueToken) {
        return QueueTokenJpaEntity.of(queueToken);
    }
}
