package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.repository.QueueTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class QueueTokenRedisRepositoryImpl implements QueueTokenRepository {

    private final TimeProvider timeProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final String QUEUE_TOKEN_WAIT_KEY = "queues:token:wait";
    private final String QUEUE_TOKEN_PROCESSED_KEY = "queues:token:proceed";

    @Override
    public String token(String token, Long time) {
        redisTemplate.opsForZSet().add(QUEUE_TOKEN_WAIT_KEY, token, timeProvider.getCurrentTimestamp());
        return token;
    }

    @Override
    public boolean findToken(String token) {
        Double processedScore = redisTemplate.opsForZSet().score(QUEUE_TOKEN_PROCESSED_KEY, token);
        Double waitScore = redisTemplate.opsForZSet().score(QUEUE_TOKEN_WAIT_KEY, token);

        // token이 processed queue에 존재하지 않으면 null 반환
        if (processedScore == null && waitScore == null) {
            return false;
        }

        return true;
    }

    @Override
    public Long rank(String token) {
        Long rank = redisTemplate.opsForZSet().rank(QUEUE_TOKEN_WAIT_KEY, token);
        return (rank != null && rank >= 0) ? rank + 1 : 0;
    }

    @Override
    public void processed(int count) {
        redisTemplate.keys(QUEUE_TOKEN_WAIT_KEY).forEach(key -> {
            allowUser(count);
        });

    }


    public void allowUser(int count) {
        Set<String> members = redisTemplate.opsForZSet().range(QUEUE_TOKEN_WAIT_KEY, 0, count - 1);
        if (members == null || members.isEmpty()) return;

        members.forEach(member -> {
            redisTemplate.opsForZSet().remove(QUEUE_TOKEN_WAIT_KEY, member);
            redisTemplate.opsForZSet().add(QUEUE_TOKEN_PROCESSED_KEY, member, timeProvider.getCurrentTimestamp());
            redisTemplate.expire(QUEUE_TOKEN_PROCESSED_KEY, 10 * 60, TimeUnit.SECONDS);
        });
    }

}
