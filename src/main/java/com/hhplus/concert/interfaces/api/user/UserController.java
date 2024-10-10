package com.hhplus.concert.interfaces.api.user;


import com.hhplus.concert.interfaces.api.user.dto.CreateUserToken;
import com.hhplus.concert.interfaces.api.user.dto.GetUserPoint;
import com.hhplus.concert.interfaces.api.user.dto.GetUserQueueRank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 유저 토큰 생성 API
     * @param request - 유저 아이디
     * @return - 토큰
     */
    @PostMapping("/token")
    public ResponseEntity<CreateUserToken.Response> createUserToken(@RequestBody CreateUserToken.Request request) {

        return ResponseEntity.ok(new CreateUserToken.Response(UUID.randomUUID().toString()));
    }

    /**
     * 대기 순서 조회
     * @param userId - 유저 아이디
     * @return - 대기 순서
     */
    @GetMapping("/{userId}/rank")
    public ResponseEntity<GetUserQueueRank.Response> getQueue(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(new GetUserQueueRank.Response(100));
    }

    /**
     * 유저 잔액 조회
     * @param userId - 유저 아이디
     * @return - 잔액
     */
    @GetMapping("/{userId}/point")
    public ResponseEntity<GetUserPoint.Response> getBalance(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(new GetUserPoint.Response(100000));
    }


}
