package com.hhplus.concert.infra.persistence.payment.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryJpaRepository extends JpaRepository<PaymentHistoryJpaEntity, Long> {

}
