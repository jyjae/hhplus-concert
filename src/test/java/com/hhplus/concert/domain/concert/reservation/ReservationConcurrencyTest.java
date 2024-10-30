package com.hhplus.concert.domain.concert.reservation;

import com.hhplus.concert.application.facade.ReservationLockFacade;
import org.junit.jupiter.api.DisplayName;
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
public class ReservationConcurrencyTest {

    @Autowired
    private ReservationLockFacade reservationFacade;

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("좌석 예약 동시성 테스트 성공 - 비관적 락")
    @Test
    public void shouldCompleteSeatReservationWithConcurrencySuccessfully() throws InterruptedException {
        // given
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger successCount = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reservationFacade.reservation(1L, 1L, 7L);  // 동시 요청
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
