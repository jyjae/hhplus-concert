package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.concertdate.ConcertDateService;
import com.hhplus.concert.application.token.FindQueueTokenQuery;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdate.ConcertDate;
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
