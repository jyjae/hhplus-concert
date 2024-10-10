package com.hhplus.concert.interfaces.api.point.dto;

public class ChargePoint {

        public record Request(String userId, int amount) {

        }

        public record Response(int balance) {

        }
}
