package com.hhplus.concert.application.payment;

import com.hhplus.concert.domain.payment.Payment;
import com.hhplus.concert.domain.payment.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @DisplayName("결제 요청 시 결제 성공")
    @Test
    void shouldCompletePaymentSuccessfullyOnPaymentRequest() {
        // Given
        PaymentCommand paymentCommand = new PaymentCommand(1L, 1L);

        // When
        when(paymentRepository.save(any(Payment.class))).thenReturn(1L);
        Long id = paymentService.payment(paymentCommand);

        // Then
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(1L);

    }

}