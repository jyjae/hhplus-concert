package com.hhplus.concert.interfaces.api.payment;

import com.hhplus.concert.interfaces.api.payment.dto.CreatePayment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    /** 결제
     * @param request - 유저 아이디, 자리 아이디
     * @return - 결제 정보
     */
    @PostMapping
    public ResponseEntity<CreatePayment.Response> createPayment(
            @RequestHeader("token") String token,
            @RequestBody CreatePayment.Request request) {
        return ResponseEntity.ok(new CreatePayment.Response(1L, 1L, 100000, "결제 완료"));
    }
}
