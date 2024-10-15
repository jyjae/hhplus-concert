package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.domain.token.QueueTokenRepository;
import com.hhplus.concert.domain.token.QueueTokenStatus;
import com.hhplus.concert.exception.NotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class QueueTokenRepositoryImpl implements QueueTokenRepository {

    private final QueueTokenJpaRepository tokenJpaRepository;
    private final QueueTokenMapper queueTokenMapper;

    @Override
    public String token(Long userId, String token) {
        return tokenJpaRepository.save(QueueTokenJpaEntity.of(userId, token, QueueTokenStatus.WAITING.getValue(), Instant.now().plus(
            Duration.ofMinutes(30)).toEpochMilli())).getToken();
    }

    @Override
    public QueueToken getToken(Long userId) {
        return queueTokenMapper.mapToDomain(tokenJpaRepository.findTokenByUserId(userId));
    }

    @Override
    public QueueToken findToken(Long userId, String token) {
        return queueTokenMapper.mapToDomainOptional(tokenJpaRepository.findTokenByUserIdAndToken(userId, token))
                .orElseThrow(() -> new NotFoundException("Token not found"));
    }

    @Override
    public Long lastProcessingToken(QueueTokenStatus queueTokenStatus) {
        return tokenJpaRepository.findLastProcessingToken(queueTokenStatus.getValue());
    }


    @Override
    public List<QueueToken> getTokens(Integer count) {
        return tokenJpaRepository.findAllTokenAsc(count).stream()
                .map(queueTokenMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(QueueToken token) {
        tokenJpaRepository.save(QueueTokenJpaEntity.of(token));
    }

    @Override
    public void processed(QueueToken queueToken) {
        QueueTokenJpaEntity queueTokenJpaEntity = queueTokenMapper.mapToEntity(queueToken);
        tokenJpaRepository.save(queueTokenJpaEntity);
    }
}
