package com.hhplus.concert.domain.point;


import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.UsePointCommand;
import com.hhplus.concert.domain.user.point.service.PointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PointConcurrencyTest {

    @Autowired
    private PointService pointService;


    @Sql({"/reset.sql", "/insert.sql"})
    @Test
    public void shouldCompleteChargePointWithConcurrencySuccessfully() throws InterruptedException {
        int threadCount = 100;  // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);  // 스레드 동기화

        AtomicInteger successCount = new AtomicInteger(0);  // 성공 횟수 카운터

        // 여러 스레드에서 동시에 충전 요청
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.charge(new ChargePointCommand(1L, 1000));  // 동시 요청
                    successCount.incrementAndGet();  // 성공 시 카운트 증가
                } catch (Exception e) {
                    System.out.println("예외 발생: " + e.getMessage());
                } finally {
                    latch.countDown();  // 스레드 작업 완료
                }
            });
        }

        latch.await();  // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown();

        // 테스트: 성공한 스레드는 최대 1개여야 함
        assertThat(successCount.get()).isEqualTo(1);
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @Test
    public void shouldCompleteUsePointWithConcurrencySuccessfully() throws InterruptedException {
        int threadCount = 100;  // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);  // 스레드 동기화

        AtomicInteger successCount = new AtomicInteger(0);  // 성공 횟수 카운터

        // 여러 스레드에서 동시에 충전 요청
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pointService.use(new UsePointCommand(1L, 1000));  // 동시 요청
                    successCount.incrementAndGet();  // 성공 시 카운트 증가
                } catch (Exception e) {
                    System.out.println("예외 발생: " + e.getMessage());
                } finally {
                    latch.countDown();  // 스레드 작업 완료
                }
            });
        }

        latch.await();  // 모든 스레드가 완료될 때까지 대기
        executorService.shutdown();

        // 테스트: 성공한 스레드는 최대 1개여야 함
        assertThat(successCount.get()).isEqualTo(1);
    }
}
