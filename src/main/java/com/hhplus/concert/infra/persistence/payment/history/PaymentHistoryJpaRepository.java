package com.hhplus.concert.infra.persistence.payment.history;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistoryJpaEntity, Long> {

    Optional<PaymentHistoryJpaEntity> findByPaymentId(Long paymentId);
}
