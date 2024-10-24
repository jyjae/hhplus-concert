package com.hhplus.concert.domain.reservation.service;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.domain.reservation.repository.ReservationRepository;
import com.hhplus.concert.domain.reservation.dto.ReservationCommand;
import com.hhplus.concert.domain.reservation.model.Reservation;
import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.InvalidException;
import com.hhplus.concert.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeProvider timeProvider;

    @Transactional
    public Long reserveConcertDateSeat(ReservationCommand command) {
        Optional<Reservation> reservationOptional = reservationRepository.findReservationExpiredDateAfter(command.getConcertDateSeatId(), timeProvider.getCurrentTimestamp());

        if (reservationOptional.isPresent()) {
            throw new AlreadyExistsException(ErrorType.SEAT_ALREADY_RESERVED);
        }

        Reservation reservation = Reservation.from(command.getUserId(), command.getPrice(), command.getConcertDateSeatId(), timeProvider.getCurrentTimestamp(), timeProvider.getCurrentInstantPlusMinutes(5));
        return reservationRepository.reserveConcertDateSeat(reservation);
    }

    @Transactional(readOnly = true)
    public Reservation getReservation(Long reservationId) {
        Reservation reservation =  reservationRepository.findReservationById(reservationId)
                . orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_RESERVATION));

        if(reservation.isExpired(timeProvider.getCurrentTimestamp())) {
            throw new InvalidException(ErrorType.RESERVATION_EXPIRED);
        }

        return reservation;
    }
}
