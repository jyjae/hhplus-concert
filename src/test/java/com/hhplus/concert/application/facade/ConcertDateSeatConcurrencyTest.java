package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.domain.concert.repository.ConcertDateSeatRepository;
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
public class ConcertDateSeatConcurrencyTest {

    @Autowired
    private ConcertDateSeatService concertDateSeatService;
    

    private final Long seatId = 1L;  // 테스트용 좌석 ID

    @Sql({"/reset.sql", "/insert.sql"})
    @Test
    public void shouldCompleteSeatReservationWithConcurrencySuccessfully() throws InterruptedException {
        int threadCount = 100;  // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);  // 스레드 동기화

        AtomicInteger successCount = new AtomicInteger(0);  // 성공 횟수 카운터

        // 여러 스레드에서 동시에 예약 완료 요청
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    concertDateSeatService.completeReservation(seatId);  // 동시 요청
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
