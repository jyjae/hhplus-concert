package com.hhplus.concert.domain.payment.listener;

import com.hhplus.concert.domain.event.PaymentHistoryEvent;
import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.domain.payment.repository.history.PaymentHistoryRepository;
import com.hhplus.concert.domain.payment.service.PaymentHistoryService;
import com.hhplus.concert.infra.persistence.payment.history.PaymentHistoryJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentHistoryEventListener {
    private final PaymentHistoryService paymentHistoryService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentHistoryEvent(PaymentHistoryEvent event) {
        // 트랜잭션 커밋 후 비동기로 결제 이력 저장
        PaymentHistory paymentHistory = PaymentHistory.of(event.getPaymentId(), event.getUserId(), event.getConcertDateSeatId());
        paymentHistoryService.save(paymentHistory);

        log.info("PaymentHistoryEvent saved: {}", paymentHistory);
    }
}
