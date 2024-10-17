package com.hhplus.concert.domain.lock;

public interface LockRepository {
    Integer acquireLock(String lockName, int timeout);
    void releaseLock(String lockName);
}
