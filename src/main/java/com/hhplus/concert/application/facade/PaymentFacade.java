package com.hhplus.concert.application.facade;

import com.hhplus.concert.domain.concert.service.ConcertDateSeatService;
import com.hhplus.concert.domain.reservation.service.ReservationService;
import com.hhplus.concert.domain.payment.dto.PaymentCommand;
import com.hhplus.concert.domain.payment.service.PaymentService;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.domain.user.point.service.PointService;
import com.hhplus.concert.domain.user.point.dto.UsePointCommand;
import com.hhplus.concert.domain.reservation.model.Reservation;
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
    private final ConcertDateSeatService concertDateSeatService;
    private final PointService pointService;

    @Transactional
    public Long payment(String token, CreatePaymentRequest request) {
        // 1. 큐 토큰 확인 및 삭제
//        queueTokenService.deleteQueueToken(token);

        // 2. 예약 정보 조회
        Reservation reservation = reservationService.getReservation(request.getReservationId());

        // 3. 포인트 차감 시도
        pointService.use(new UsePointCommand(request.getUserId(), reservation.getPrice()));

        // 4. 결제 처리
        Long paymentId = paymentService.payment(new PaymentCommand(
                request.getUserId(), request.getReservationId()));

        // 5. 좌석 예약 확정
        concertDateSeatService.completeReservation(reservation.getConcertDateSeatId());

        return paymentId;

    }
}
