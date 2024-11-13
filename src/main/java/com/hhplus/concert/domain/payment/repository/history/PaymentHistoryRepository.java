package com.hhplus.concert.domain.payment.repository.history;

import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.infra.persistence.payment.history.PaymentHistoryJpaEntity;

import java.util.Optional;

public interface PaymentHistoryRepository {
    void save(PaymentHistory paymentHistory);

    Optional<PaymentHistory> findByPaymentId(Long paymentId);
}
