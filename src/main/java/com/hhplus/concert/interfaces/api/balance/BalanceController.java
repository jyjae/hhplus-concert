package com.hhplus.concert.interfaces.api.balance;

import com.hhplus.concert.interfaces.api.balance.dto.ChargeBalance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    /** 잔액 충전 API
    * @param request - 유저 아이디, 충전 금액
    * @return - 충전 후 잔액
    **/
    @PatchMapping
    public ResponseEntity<ChargeBalance.Response> chargeBalance(@RequestBody ChargeBalance.Request request) {
        return ResponseEntity.ok(new ChargeBalance.Response(100000));
    }

}
