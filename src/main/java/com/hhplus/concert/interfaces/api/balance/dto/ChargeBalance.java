package com.hhplus.concert.interfaces.api.balance.dto;

public class ChargeBalance {

        public record Request(String userId, int amount) {

        }

        public record Response(int balance) {

        }
}
