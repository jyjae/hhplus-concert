package com.hhplus.concert.interfaces.api.concert.concertdate;

import com.hhplus.concert.interfaces.api.concert.concertdate.dto.GetConcertDateResponse;
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

@Tag(name = "ConcertDate", description = "콘서트 날짜 관련 API")
@RequestMapping("/concerts/{concertId}/concert-dates")
public interface ConcertDateApi {

    @Operation(
            summary = "예약 가능한 콘서트 날짜 조회",
            description = "콘서트 ID와 인증 토큰을 통해 예약 가능한 날짜 목록을 조회합니다.",
            parameters = {
                    @Parameter(
                            name = "token",
                            description = "대기열 토큰 (헤더)",
                            required = true,
                            in = ParameterIn.HEADER,
                            schema = @Schema(type = "string", example = "984382e4-4a0b-4228-a9e4-b45e180b4c39")
                    ),
                    @Parameter(
                            name = "concertId",
                            description = "조회할 콘서트의 ID",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", example = "1")
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<GetConcertDateResponse>> getConcertDates(
            @RequestHeader("token") String token,
            @PathVariable("concertId") Long concertId
    );
}
