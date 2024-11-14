package com.hhplus.concert.infra;
import com.hhplus.concert.domain.MockApiClient;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MockApiClientImpl implements MockApiClient {

    @Override
    public boolean sendSlack(Long paymentId, Long reservationId, Long concertDateSeatId) {
        try {
            log.info("[SUCCESS] Send info to Slack");
        } catch (RuntimeException e) {
            throw new RuntimeException("[FAIL] Send info to Slack", e);
        }
        return true;
    }
}