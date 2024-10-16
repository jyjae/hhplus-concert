package com.hhplus.concert.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
}
