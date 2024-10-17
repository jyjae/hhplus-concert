package com.hhplus.concert.infra.persistence.lock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface NamedLockJpaRepository extends CrudRepository<NamedLockJpaEntity, Long> {

    @Query(value = "SELECT GET_LOCK(:lockName, :timeout)", nativeQuery = true)
    Integer acquireLock(String lockName, int timeout);

    @Query(value = "SELECT RELEASE_LOCK(:lockName)", nativeQuery = true)
    void releaseLock(String lockName);
}