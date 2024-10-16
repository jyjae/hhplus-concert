package com.hhplus.concert.domain.token;

import com.hhplus.concert.infra.persistence.token.QueueTokenJpaEntity;

import java.util.List;

public interface QueueTokenRepository {
    String token(Long userId, String uuid, Long time);

    QueueToken getToken(Long userId);

    QueueToken findToken(String token, Long currentTime);

    long lastProcessingToken(QueueTokenStatus queueTokenStatus);

    List<QueueToken> getTokens(Integer count);

    void save(QueueToken token);

  void processed(QueueToken token);

    void delete(String token);
}
