package com.hhplus.concert.infra.persistence.payment;

import com.hhplus.concert.domain.payment.Payment;
import com.hhplus.concert.domain.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;


    @Override
    public Long save(Payment payment) {
        return paymentJpaRepository.save(PaymentJpaEntity.from(payment)).getId();
    }
}
