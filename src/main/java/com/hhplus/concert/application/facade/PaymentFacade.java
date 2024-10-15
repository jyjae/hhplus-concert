package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.concert.reservation.ReservationService;
import com.hhplus.concert.application.payment.PaymentCommand;
import com.hhplus.concert.application.payment.PaymentService;
import com.hhplus.concert.application.token.FindQueueTokenQuery;
import com.hhplus.concert.application.token.QueueTokenService;
import com.hhplus.concert.application.user.point.PointService;
import com.hhplus.concert.application.user.point.UsePointCommand;
import com.hhplus.concert.domain.concert.reservation.Reservation;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentFacade {

    private final PaymentService paymentService;
    private final QueueTokenService queueTokenService;
    private final ReservationService reservationService;
    private final PointService pointService;

    @Transactional
    public Long payment(String token, CreatePaymentRequest request) {
        queueTokenService.findQueueToken(new FindQueueTokenQuery(token));
        Reservation reservation = reservationService.getReservation(request.getReservationId());
        pointService.use(new UsePointCommand(request.getUserId(), reservation.getPrice()));
        return paymentService.payment(new PaymentCommand(request.getUserId(), request.getReservationId()));
    }
}
