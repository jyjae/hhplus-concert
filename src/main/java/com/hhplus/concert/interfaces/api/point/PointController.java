package com.hhplus.concert.interfaces.api.point;

import com.hhplus.concert.interfaces.api.point.dto.ChargePoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
public class PointController {

    /** 잔액 충전 API
    * @param request - 유저 아이디, 충전 금액
    * @return - 충전 후 잔액
    **/
    @PatchMapping
    public ResponseEntity<ChargePoint.Response> chargeBalance(@RequestBody ChargePoint.Request request) {
        return ResponseEntity.ok(new ChargePoint.Response(100000));
    }

}
