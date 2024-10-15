package com.hhplus.concert.application.payment;

import com.hhplus.concert.domain.payment.Payment;
import com.hhplus.concert.domain.payment.PaymentRepository;
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
