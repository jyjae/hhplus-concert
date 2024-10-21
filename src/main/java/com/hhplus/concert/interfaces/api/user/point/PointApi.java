package com.hhplus.concert.interfaces.api.user.point;

import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Point", description = "포인트 관리 API")
@RequestMapping("/users")
public interface PointApi {


    @Operation(
            summary = "잔액 충전",
            description = "사용자의 포인트 잔액을 충전합니다.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "유저 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    )
            }
    )
    @PatchMapping("/{userId}/points")
    ResponseEntity<ChargePointResponse> charge(
            @PathVariable("userId") Long userId,
            @RequestBody ChargePointRequest request
    );


    @Operation(
            summary = "잔액 조회",
            description = "사용자의 현재 포인트 잔액을 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "유저 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    )
            }
    )
    @GetMapping("/{userId}/points")
    ResponseEntity<Integer> point(
            @PathVariable("userId") Long userId
    );
}
