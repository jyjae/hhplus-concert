package com.hhplus.concert.infra.persistence.payment.history;

import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.domain.payment.repository.history.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentHistoryRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryJpaRepository paymentHistoryJpaRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentHistoryJpaRepository.save(PaymentHistoryJpaEntity.from(paymentHistory));
    }
}
