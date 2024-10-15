package com.hhplus.concert.interfaces.api.payment;

import com.hhplus.concert.application.facade.PaymentFacade;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentRequest;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentFacade paymentFacade;

    /** 결제
     * @param request - 유저 아이디, 자리 아이디
     * @return - 결제 정보
     */
    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(
            @RequestHeader("token") String token,
            @RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(new CreatePaymentResponse(paymentFacade.payment(token, request)));
    }
}
