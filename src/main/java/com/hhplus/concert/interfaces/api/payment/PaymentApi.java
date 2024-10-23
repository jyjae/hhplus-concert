package com.hhplus.concert.interfaces.api.payment;

import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentRequest;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment", description = "결제 관련 API")
@RequestMapping("/payments")
public interface PaymentApi {

    /**
     * 결제 생성 API
     *
     * @param token   사용자 인증 토큰
     * @param request 결제 요청 DTO
     * @return 결제 응답 DTO
     */
    @Operation(
            summary = "결제 생성",
            description = "유저와 자리 정보를 기반으로 결제를 생성합니다.",
            parameters = {
                    @Parameter(
                            name = "X-Access-Token",
                            description = "대기열 토큰",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "984382e4-4a0b-4228-a9e4-b45e180b4c39")
                    )
            }
    )
    @PostMapping
    ResponseEntity<CreatePaymentResponse> createPayment(
            @RequestHeader("X-Access-Token") String token,
            @RequestBody CreatePaymentRequest request
    );
}
