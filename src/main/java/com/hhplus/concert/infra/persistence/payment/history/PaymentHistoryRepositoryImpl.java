package com.hhplus.concert.infra.persistence.payment.history;

import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.domain.payment.repository.history.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PaymentHistoryRepositoryImpl implements PaymentHistoryRepository {

    private final PaymentHistoryJpaRepository paymentHistoryJpaRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentHistoryJpaRepository.save(PaymentHistoryJpaEntity.from(paymentHistory));
    }

    @Override
    public Optional<PaymentHistory> findByPaymentId(Long paymentId) {
        return paymentHistoryJpaRepository.findByPaymentId(paymentId)
                .map(PaymentHistoryJpaEntity::toDomain);
    }
}
