package com.hhplus.concert.domain.user.repository;

import com.hhplus.concert.domain.user.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
}
