package com.hhplus.concert.infra.persistence.lock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NamedLockJpaEntity {

    @Id
    private Long id;
}
