package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.concertdate.service.ConcertDateService;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdate.model.ConcertDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertDateFacade {

    private final ConcertDateService concertDateService;
    private final QueueTokenService queueTokenService;

    public List<ConcertDate> concertDates(Long concertId, String token) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        return concertDateService.getConcertDatesLessThanMaxCapacity(concertId);
    }

}
