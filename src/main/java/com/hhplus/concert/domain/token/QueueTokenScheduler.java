package com.hhplus.concert.domain.token;

import com.hhplus.concert.domain.config.service.ConfigService;
import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class QueueTokenScheduler {

    private final QueueTokenService queueTokenService;
    private final ConfigService configService;

    @Value("${queue.token.scheduling}")
    private Boolean scheduling;

    public QueueTokenScheduler(
            QueueTokenService queueTokenService,
            ConfigService configService
    ) {
        this.queueTokenService = queueTokenService;
        this.configService = configService;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 100000)
    public void scheduleProcessQueue() {
         if (!scheduling) {
            return;
        }

        int count = configService.getMaxQueueTokens();
        queueTokenService.processQueueTokens(count);
    }
}