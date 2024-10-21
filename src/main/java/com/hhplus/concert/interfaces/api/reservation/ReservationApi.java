package com.hhplus.concert.interfaces.api.reservation;

import com.hhplus.concert.interfaces.api.reservation.dto.ReservationRequest;
import com.hhplus.concert.interfaces.api.reservation.dto.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservation", description = "콘서트 좌석 예약 API")
@RequestMapping("/reservations")
public interface ReservationApi {

    @Operation(
            summary = "콘서트 좌석 예약",
            description = "콘서트 날짜와 좌석 정보를 기반으로 예약을 생성합니다.",
            parameters = {
                    @Parameter(
                            name = "token",
                            description = "대기열 토큰",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "984382e4-4a0b-4228-a9e4-b45e180b4c39")
                    )
            }
    )
    @PostMapping()
    ResponseEntity<ReservationResponse> reserveConcertDateSeat(
            @RequestHeader("token") String token,
            @RequestBody ReservationRequest request
    );
}

