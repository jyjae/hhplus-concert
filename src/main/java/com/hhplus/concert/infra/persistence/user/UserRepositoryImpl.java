package com.hhplus.concert.infra.persistence.user;

import com.hhplus.concert.domain.user.User;
import com.hhplus.concert.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<User> findById(Long userId) {
        return Optional.empty();
    }
}
