package com.hhplus.concert.interfaces.api.token;

import com.hhplus.concert.application.facade.QueueTokenFacade;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenRequest;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueueTokenController implements QueueTokenApi{

    private final QueueTokenFacade queueTokenFacadeService;

    /**
     * 대기열 발급 API
     * @param request - 유저 아이디
     * @return - 토큰
     */
    @Override
    public ResponseEntity<QueueTokenResponse> generateToken(@RequestBody QueueTokenRequest request) {
        return ResponseEntity.ok(new QueueTokenResponse(queueTokenFacadeService.token(request.getUserId())));
    }

}
