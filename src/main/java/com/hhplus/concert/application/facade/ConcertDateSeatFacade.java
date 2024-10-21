package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.concertdateseat.service.ConcertDateSeatService;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.concert.concertdateseat.model.ConcertDateSeat;
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
