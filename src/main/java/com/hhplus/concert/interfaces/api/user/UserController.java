package com.hhplus.concert.interfaces.api.user;


import com.hhplus.concert.application.facade.UserFacade;
import com.hhplus.concert.interfaces.api.user.dto.GetQueueResponse;
import com.hhplus.concert.interfaces.api.user.dto.GetUserPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserFacade userFacadeService;

    /**
     * 대기 순서 조회
     * @param userId - 유저 아이디
     * @return - 대기 순서
     */
    @Override
    public ResponseEntity<GetQueueResponse> getQueue(
            @RequestHeader("X-Access-Token") String token,
            @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(new GetQueueResponse(userFacadeService.rank(userId, token)));
    }

}
