package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.concertdateseat.ConcertDateSeatService;
import com.hhplus.concert.application.token.FindQueueTokenQuery;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertDateSeatFacade {

    private final ConcertDateSeatService concertDateSeatService;
    private final QueueTokenService queueTokenService;

    public List<ConcertDateSeat> concertDateSeats(String token, Long concertDateId) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));

        return concertDateSeatService.getAvailableSeats(concertDateId);
    }
}
