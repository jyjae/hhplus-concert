package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateSeatResponse;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateResponse;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Concert", description = "콘서트 관련 API")
@RequestMapping("/concerts")
public interface ConcertApi {

    @Operation(
            summary = "콘서트 전체 조회",
            description = "모든 콘서트 정보를 조회합니다."
    )
    @GetMapping
    ResponseEntity<List<GetConcertResponse>> getConcerts();

    @Operation(
            summary = "예약 가능한 콘서트 날짜 조회",
            description = "콘서트 ID와 인증 토큰을 통해 예약 가능한 날짜 목록을 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "concertId",
                            description = "조회할 콘서트의 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    )
            }
    )
    @GetMapping("/{concertId}/concert-dates")
    ResponseEntity<List<GetConcertDateResponse>> getConcertDates(
            @PathVariable("concertId") Long concertId
    );

    @Operation(
            summary = "예약 가능한 좌석 목록 조회",
            description = "콘서트 날짜별로 예약 가능한 좌석 목록을 조회합니다.",
            parameters = {
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
    @GetMapping("/{concertId}/concert-dates/{concertDateId}/seats")
    ResponseEntity<List<GetConcertDateSeatResponse>> getAvailableSeats(
            @PathVariable("concertId") Long concertId,
            @PathVariable("concertDateId") Long concertDateId
    );
}
