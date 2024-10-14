package com.hhplus.concert.application.token;

import com.hhplus.concert.application.config.ConfigService;
import com.hhplus.concert.domain.token.QueueToken;
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

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void scheduleProcessQueue() {
        if (!scheduling) {
            return;
        }
        log.info("Processing queue tokens");

        Integer count = configService.getMaxQueueTokens();
        List<QueueToken> queueTokens = queueTokenService.getQueueTokens(count);
        queueTokenService.processQueueTokens(queueTokens);
    }
}