package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.application.facade.ConcertFacade;
import com.hhplus.concert.domain.concert.Concert;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertController implements ConcertApi {

    private final ConcertFacade concertFacade;

    /**
     * 콘서트 전체 조회 API
     * @return - 콘서트 목록
     */
    @Override
    public ResponseEntity<List<GetConcertResponse>> getConcerts() {
        return ResponseEntity.ok(concertFacade.concerts().stream()
                .map(concert -> new GetConcertResponse(
                        concert.getId(),
                        concert.getName(),
                        concert.getStartDate(),
                        concert.getEndDate())).toList());
    }

}
