package com.hhplus.concert.infra.persistence.token;

import java.util.List;
import java.util.Optional;

public interface QueueTokenJpaCustomRepository {
    QueueTokenJpaEntity findTokenByUserId(Long userId);

    Long findLastProcessingToken(String queueTokenStatus);

    List<QueueTokenJpaEntity> findAllTokenAsc(int count);

    Optional<QueueTokenJpaEntity> findTokenByToken(String token, Long currentTime);
}
