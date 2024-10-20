package com.hhplus.concert.application.user;

import com.hhplus.concert.domain.user.UserRepository;
import com.hhplus.concert.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @DisplayName("유저 조회 시 유저가 존재하지 않아 예외가 발생하는 테스트")
    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        // given
        FindUserCommand command = new FindUserCommand(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when&then
        assertThatThrownBy(()->userService.findUser(command))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User not found");

    }



}