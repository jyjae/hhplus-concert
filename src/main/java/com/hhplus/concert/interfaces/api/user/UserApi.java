package com.hhplus.concert.interfaces.api.user;

import com.hhplus.concert.interfaces.api.user.dto.GetQueueResponse;
import com.hhplus.concert.interfaces.api.user.dto.GetUserPointResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 관리 API")
@RequestMapping("/users")
public interface UserApi {

    /**
     * 대기 순서 조회 API
     *
     * @param token  사용자 인증 토큰
     * @param userId 유저 ID
     * @return 유저의 대기 순서
     */
    @Operation(
            summary = "대기 순서 조회",
            description = "유저의 대기 순서를 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "token",
                            description = "대기열 토큰",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "984382e4-4a0b-4228-a9e4-b45e180b4c39")
                    ),
                    @Parameter(
                            name = "userId",
                            description = "유저 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    )
            }
    )
    @GetMapping("/{userId}/rank")
    ResponseEntity<GetQueueResponse> getQueue(
            @RequestHeader("token") String token,
            @PathVariable("userId") Long userId
    );

    /**
     * 유저 잔액 조회 API
     *
     * @param token  사용자 인증 토큰
     * @param userId 유저 ID
     * @return 유저의 잔액 정보 응답 DTO
     */
    @Operation(
            summary = "유저 잔액 조회",
            description = "유저의 현재 잔액을 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "token",
                            description = "대기열 토큰",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "984382e4-4a0b-4228-a9e4-b45e180b4c39")
                    ),
                    @Parameter(
                            name = "userId",
                            description = "유저 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string", example = "1")
                    )
            }
    )
    @GetMapping("/{userId}/point")
    ResponseEntity<GetUserPointResponse> getBalance(
            @RequestHeader("token") String token,
            @PathVariable("userId") Long userId
    );
}
