package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.service.ConcertDateService;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConcertDateFacade {

    private final ConcertDateService concertDateService;
    private final QueueTokenService queueTokenService;



}
