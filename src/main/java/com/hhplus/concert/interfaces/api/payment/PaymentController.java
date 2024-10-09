package com.hhplus.concert.interfaces.api.payment;

import com.hhplus.concert.interfaces.api.payment.dto.CreatePayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    /** 결제
     * @param request - 유저 아이디, 자리 아이디
     * @return - 결제 정보
     */
    @PostMapping
    public ResponseEntity<CreatePayment.Response> createPayment(@RequestBody CreatePayment.Request request) {
        return ResponseEntity.ok(new CreatePayment.Response(1L, 1L, 100000, "결제 완료"));
    }
}
