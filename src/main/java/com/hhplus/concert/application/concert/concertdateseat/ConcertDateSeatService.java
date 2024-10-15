package com.hhplus.concert.application.concert.concertdateseat;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertDateSeatService {

    private final ConcertDateSeatRepository concertDateSeatRepository;
    private final TimeProvider timeProvider;

    @Transactional(readOnly = true)
    public List<ConcertDateSeat> getAvailableSeats(long concertDateId) {
        return concertDateSeatRepository.concertDateSeat(concertDateId, timeProvider.getCurrentTimestamp())
                .stream()
                .filter(ConcertDateSeat::isAvailable)
                .filter(seat -> seat.getExpiredDate() > timeProvider.getCurrentTimestamp())
                .toList();
    }
}
