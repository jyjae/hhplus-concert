package com.hhplus.concert.domain.payment.model;

public enum PaymentStatus {
    PAYMENT_REQUESTED("PAYMENT_REQUESTED"),
    PAYMENT_COMPLETED("PAYMENT_COMPLETED"),
    PAYMENT_FAILED("PAYMENT_FAILED");

    private String value;

    PaymentStatus(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }
}
