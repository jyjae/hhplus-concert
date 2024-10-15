package com.hhplus.concert.application.concert.reservation;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.concert.reservation.Reservation;
import com.hhplus.concert.domain.concert.reservation.ReservationRepository;
import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeProvider timeProvider;

    public Long reserveConcertDateSeat(ReservationCommand command) {
        Optional<Reservation> reservationOptional = reservationRepository.findReservation(command.getConcertDateSeatId());

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            if(!reservation.isExpired(timeProvider.getCurrentTimestamp())) {
                throw new AlreadyExistsException("임시 예약된 좌석입니다.");
            }
        }

        Reservation reservation = Reservation.from(command.getUserId(), command.getPrice(), command.getConcertDateSeatId(), timeProvider.getCurrentTimestamp(), timeProvider.getCurrentInstantPlusMinutes(5));
        return reservationRepository.reserveConcertDateSeat(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        return reservationRepository.findReservationById(reservationId)
                .orElseThrow(() -> new NotFoundException("no reservation information"));
    }
}
