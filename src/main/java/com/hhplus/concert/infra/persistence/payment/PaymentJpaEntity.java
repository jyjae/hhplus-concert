package com.hhplus.concert.infra.persistence.payment;

import com.hhplus.concert.domain.payment.model.Payment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity(name = "payment")
public class PaymentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "status")
    private String status;

    public PaymentJpaEntity(Long userId, Long reservationId, String name) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.status = name;
    }


    public static PaymentJpaEntity from(Payment payment) {
        return new PaymentJpaEntity(payment.getUserId(), payment.getReservationId(), payment.getStatus().getValue());
    }
}
