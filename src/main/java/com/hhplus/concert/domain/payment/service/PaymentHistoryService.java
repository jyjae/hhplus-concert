package com.hhplus.concert.domain.payment.service;

import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import com.hhplus.concert.domain.payment.repository.history.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(PaymentHistory paymentHistory) {
        paymentHistoryRepository.save(paymentHistory);
    }

    public Optional<PaymentHistory> findByPaymentId(Long paymentId) {
        return paymentHistoryRepository.findByPaymentId(paymentId);
    }
}
