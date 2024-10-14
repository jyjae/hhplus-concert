package com.hhplus.concert.infra.persistence.token;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface QueueTokenJpaCustomRepository {
    QueueTokenJpaEntity findTokenByUserId(Long userId);

    Optional<QueueTokenJpaEntity> findTokenByUserIdAndToken(Long userId, String token);

    Long findLastProcessingToken(String queueTokenStatus);

    List<QueueTokenJpaEntity> findAllTokenAsc(int count);
}
