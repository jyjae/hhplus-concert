package com.hhplus.concert.domain.concert.service;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.repository.ConcertDateSeatRepository;
import com.hhplus.concert.domain.concert.dto.FindConcertDateSeatQuery;
import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.NotFoundException;
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

    @Transactional(readOnly = true)
    public ConcertDateSeat findAvailableConcertDateSeat(FindConcertDateSeatQuery query) {
        return concertDateSeatRepository.findAvailableConcertDateSeat(query.getConcertDateId(), query.getConcertDateSeatId())
                .orElseThrow(() -> new AlreadyExistsException(DomainErrorType.SEAT_ALREADY_RESERVED));
    }

    @Transactional
    public void completeReservation(Long concertDateSeatId) {
        ConcertDateSeat concertDateSeat = concertDateSeatRepository
                .concertDateSeatById(concertDateSeatId)
                .orElseThrow(() -> new NotFoundException(DomainErrorType.NOT_FOUND_CONCERT_DATE_SEAT));
        concertDateSeatRepository.save(concertDateSeat.completeReservation());
    }
}
