package com.hhplus.concert.interfaces.api.token;

import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenRequest;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenResponse;
import com.hhplus.concert.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueueTokenController implements QueueTokenApi{

    private final QueueTokenService queueTokenService;

    /**
     * 대기열 발급 API
     * @param request - 유저 아이디
     * @return - 토큰
     */
    @Override
    public ResponseEntity<QueueTokenResponse> generateToken(@RequestBody QueueTokenRequest request) {

        CreateQueueTokenCommand command = new CreateQueueTokenCommand(request.getUserId(), UuidUtil.generateUuid());
        return ResponseEntity.ok(new QueueTokenResponse(queueTokenService.createQueueToken(command)));
    }

}
