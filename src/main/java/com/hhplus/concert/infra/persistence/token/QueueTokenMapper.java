package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.domain.token.QueueTokenStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueueTokenMapper {

    public QueueToken mapToDomain(QueueTokenJpaEntity entity) {
        return QueueToken.of(entity.getId(), entity.getUserId(), entity.getToken(), entity.getCreatedAt(), QueueTokenStatus.valueOf(entity.getStatus()));
    }

    public Optional<QueueToken> mapToDomainOptional(Optional<QueueTokenJpaEntity> entityOptional) {
        return entityOptional.map(this::mapToDomain);
    }

}
