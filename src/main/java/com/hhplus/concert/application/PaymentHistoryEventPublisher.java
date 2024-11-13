package com.hhplus.concert.application;

import com.hhplus.concert.domain.event.PaymentHistoryEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PaymentHistoryEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public PaymentHistoryEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishPaymentHistoryEvent(PaymentHistoryEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}