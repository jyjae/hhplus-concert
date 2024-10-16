package com.hhplus.concert.infra.persistence.payment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
}
