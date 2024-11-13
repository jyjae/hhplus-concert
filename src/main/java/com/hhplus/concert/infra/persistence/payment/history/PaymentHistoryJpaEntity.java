package com.hhplus.concert.infra.persistence.payment.history;


import com.hhplus.concert.domain.payment.model.history.PaymentHistory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment_history")
@Entity
public class PaymentHistoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long paymentId;
    private Long userId;
    private Long concertDateSeatId;

    public PaymentHistoryJpaEntity(Long paymentId, Long userId, Long concertDateSeatId) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.concertDateSeatId = concertDateSeatId;
    }

    public static PaymentHistoryJpaEntity from(PaymentHistory paymentHistory) {
        return new PaymentHistoryJpaEntity(
                paymentHistory.getPaymentId(),
                paymentHistory.getUserId(),
                paymentHistory.getConcertDateSeatId()
        );
    }

    public PaymentHistory toDomain() {
        return PaymentHistory.builder()
                .id(id)
                .paymentId(paymentId)
                .userId(userId)
                .concertDateSeatId(concertDateSeatId)
                .build();
    }
}