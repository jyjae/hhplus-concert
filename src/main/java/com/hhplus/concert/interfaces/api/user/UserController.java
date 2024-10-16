package com.hhplus.concert.interfaces.api.user;


import com.hhplus.concert.application.facade.UserFacade;
import com.hhplus.concert.interfaces.api.user.dto.GetUserPointQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacadeService;

    /**
     * 대기 순서 조회
     * @param userId - 유저 아이디
     * @return - 대기 순서
     */
    @GetMapping("/{userId}/rank")
    public ResponseEntity<Long> getQueue(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userFacadeService.rank(userId, token));
    }

    /**
     * 유저 잔액 조회
     * @param userId - 유저 아이디
     * @return - 잔액
     */
    @GetMapping("/{userId}/point")
    public ResponseEntity<GetUserPointQuery.Response> getBalance(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(new GetUserPointQuery.Response(100000));
    }


}
