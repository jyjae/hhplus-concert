package com.hhplus.concert.interfaces.api.user.point;

import com.hhplus.concert.domain.user.point.dto.ChargePointCommand;
import com.hhplus.concert.domain.user.point.dto.GetPointQuery;
import com.hhplus.concert.domain.user.point.service.PointService;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PointController implements PointApi {

    private final PointService pointService;

    /** 잔액 충전 API
    * @param request - 유저 아이디, 충전 금액
    * @return - 충전 후 잔액
    **/
    @Override()
    public ResponseEntity<ChargePointResponse> charge(
            @PathVariable(name = "userId") Long userId,
            @RequestBody ChargePointRequest request) {
        ChargePointCommand command = new ChargePointCommand(userId, request.getPoint());
        return ResponseEntity.ok(new ChargePointResponse(pointService.charge(command)));
    }

    /**
     * 잔액 조회 API
     *
     * @param userId 유저 ID
     * @return 현재 포인트 잔액
     */
    @Override()
    public ResponseEntity<Integer> point(
            @PathVariable(name = "userId") Long userId) {
        GetPointQuery query = new GetPointQuery(userId);
        return ResponseEntity.ok(pointService.point(query));
    }

}
