package com.hhplus.concert.domain.payment.repository.history;

import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.infra.persistence.payment.history.PaymentHistoryJpaEntity;

public interface PaymentHistoryRepository {
    void save(PaymentHistory paymentHistory);
}
