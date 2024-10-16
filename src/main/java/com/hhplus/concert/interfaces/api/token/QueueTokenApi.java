package com.hhplus.concert.interfaces.api.token;

import com.hhplus.concert.interfaces.api.token.dto.QueueTokenRequest;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "QueueToken", description = "대기열 토큰 발급 API")
@RequestMapping("/tokens")
public interface QueueTokenApi {

    @Operation(
            summary = "대기열 토큰 발급",
            description = "유저 ID를 기반으로 대기열 토큰을 발급합니다."
    )
    @PostMapping
    ResponseEntity<QueueTokenResponse> generateToken(@RequestBody QueueTokenRequest request);
}
