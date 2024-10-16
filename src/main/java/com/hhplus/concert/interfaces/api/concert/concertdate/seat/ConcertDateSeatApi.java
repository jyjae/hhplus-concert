package com.hhplus.concert.interfaces.api.concert.concertdate.seat;

import com.hhplus.concert.interfaces.api.concert.concertdate.seat.dto.GetConcertDateSeatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ConcertDateSeat", description = "콘서트 날짜 좌석 관련 API")
@RequestMapping("/concerts/{concertId}/concert-dates/{concertDateId}/seats")
public interface ConcertDateSeatApi {

    @Operation(
            summary = "예약 가능한 좌석 목록 조회",
            description = "콘서트 날짜별로 예약 가능한 좌석 목록을 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "token",
                            description = "사용자 인증 토큰",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "user-access-token-123")
                    ),
                    @Parameter(
                            name = "concertId",
                            description = "콘서트 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    ),
                    @Parameter(
                            name = "concertDateId",
                            description = "콘서트 날짜 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "10")
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<GetConcertDateSeatResponse>> getAvailableSeats(
            @RequestHeader("token") String token,
            @PathVariable("concertId") Long concertId,
            @PathVariable("concertDateId") Long concertDateId
    );
}
