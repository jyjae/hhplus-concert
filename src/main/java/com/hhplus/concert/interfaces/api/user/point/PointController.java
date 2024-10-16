package com.hhplus.concert.interfaces.api.user.point;

import com.hhplus.concert.application.facade.PointFacade;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{userId}/points")
public class PointController {

    private final PointFacade pointFacade;

    /** 잔액 충전 API
    * @param request - 유저 아이디, 충전 금액
    * @return - 충전 후 잔액
    **/
    @PatchMapping()
    public ResponseEntity<ChargePointResponse> charge(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") Long userId,
            @RequestBody ChargePointRequest request) {
        return ResponseEntity.ok(new ChargePointResponse(pointFacade.charge(token, userId, request)));
    }

    @GetMapping()
    public ResponseEntity<Integer> point(
            @RequestHeader("token") String token,
            @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(pointFacade.point(token, userId));
    }

}
