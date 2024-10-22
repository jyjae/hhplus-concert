package com.hhplus.concert.domain.user.service;

import com.hhplus.concert.domain.error.DomainErrorType;
import com.hhplus.concert.domain.user.dto.FindUserCommand;
import com.hhplus.concert.domain.user.model.User;
import com.hhplus.concert.domain.user.repository.UserRepository;
import com.hhplus.concert.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(FindUserCommand command) {
        return userRepository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException(DomainErrorType.NOT_FOUND_USER));
    }

}
