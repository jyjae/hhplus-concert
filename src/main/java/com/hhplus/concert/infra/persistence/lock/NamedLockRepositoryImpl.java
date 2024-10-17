package com.hhplus.concert.infra.persistence.lock;

import com.hhplus.concert.domain.lock.LockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NamedLockRepositoryImpl implements LockRepository {

    private final NamedLockJpaRepository namedLockRepository;

    @Override
    public Integer acquireLock(String lockName, int timeout) {
        return namedLockRepository.acquireLock(lockName, timeout);
    }

    @Override
    public void releaseLock(String lockName) {
        namedLockRepository.releaseLock(lockName);
    }
}
