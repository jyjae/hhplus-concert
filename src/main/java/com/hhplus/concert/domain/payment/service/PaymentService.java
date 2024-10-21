package com.hhplus.concert.domain.payment.service;

import com.hhplus.concert.domain.payment.dto.PaymentCommand;
import com.hhplus.concert.domain.payment.model.Payment;
import com.hhplus.concert.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public Long payment(PaymentCommand command) {
        Payment payment = Payment.of(command.getUserId(), command.getReservationId());
        return paymentRepository.save(payment);
    }
}
