package com.hhplus.concert.domain.point;


import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.UsePointCommand;
import com.hhplus.concert.domain.user.point.service.PointService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class PointConcurrencyTest {

    private static final Logger log = LoggerFactory.getLogger(PointConcurrencyTest.class);

    @Autowired
    private PointService pointService;



    @DisplayName("포인트 충전 동시성 테스트 성공 - 낙관적 락")
    @Sql({"/reset.sql", "/insert.sql"})
    @Test
    public void shouldCompleteChargePointWithConcurrencySuccessfully() throws InterruptedException {
        int threadCount = 100; // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger(0);

        // 여러 스레드에서 동시에 충전 요청
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.charge(new ChargePointCommand(1L, 1000));
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    log.info("충돌 발생: {}", e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        assertThat(successCount.get()).isEqualTo(1);
    }

}
