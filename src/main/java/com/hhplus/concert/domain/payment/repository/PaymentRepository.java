package com.hhplus.concert.domain.payment.repository;

import com.hhplus.concert.domain.payment.model.Payment;

public interface PaymentRepository {

    Long save(Payment payment);
}
