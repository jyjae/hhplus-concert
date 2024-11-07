package com.hhplus.concert;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class AbstractRedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    public void clearRedisData() {
        redisTemplate.getConnectionFactory().getConnection().flushDb(); // Redis 데이터 초기화
    }
}
