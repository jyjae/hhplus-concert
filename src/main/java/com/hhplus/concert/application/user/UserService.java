package com.hhplus.concert.application.user;

import com.hhplus.concert.domain.user.User;
import com.hhplus.concert.domain.user.UserRepository;
import com.hhplus.concert.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(FindUserCommand command) {
        return userRepository.findById(command.userId())
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
