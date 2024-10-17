package com.hhplus.concert.application.lock;

import com.hhplus.concert.infra.persistence.lock.NamedLockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NamedLockService {

    private final NamedLockJpaRepository namedLockRepository;

    public boolean acquireLock(String lockName, int timeout) {
        Integer result = namedLockRepository.acquireLock(lockName, timeout);
        return result != null && result == 1;
    }

    public void releaseLock(String lockName) {
        namedLockRepository.releaseLock(lockName);
    }
}