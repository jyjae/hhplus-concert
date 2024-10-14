package com.hhplus.concert.interfaces.api.token;

import com.hhplus.concert.application.facade.QueueTokenFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class QueueTokenController {

    private final QueueTokenFacade queueTokenFacadeService;

    /**
     * 대기열 발급 API
     * @param request - 유저 아이디
     * @return - 토큰
     */
    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody UserIdRequest request) {
        return ResponseEntity.ok(queueTokenFacadeService.token(request.getUserId()));
    }

}
