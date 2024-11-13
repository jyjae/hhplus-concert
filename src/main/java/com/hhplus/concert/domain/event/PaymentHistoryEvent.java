package com.hhplus.concert.domain.event;

public class PaymentHistoryEvent {
    private final Long paymentId;
    private final Long userId;
    private final Long concertDateSeatId;

    public PaymentHistoryEvent(Long paymentId, Long userId, Long concertDateSeatId) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.concertDateSeatId = concertDateSeatId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getConcertDateSeatId() {
        return concertDateSeatId;
    }
}
