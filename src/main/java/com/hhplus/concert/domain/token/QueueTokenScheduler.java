package com.hhplus.concert.domain.token;

import com.hhplus.concert.domain.config.service.ConfigService;
import com.hhplus.concert.domain.lock.NamedLockService;
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
    private final NamedLockService namedLockService;

    @Value("${queue.token.scheduling}")
    private Boolean scheduling;

    public QueueTokenScheduler(
            QueueTokenService queueTokenService,
            ConfigService configService, NamedLockService namedLockService
    ) {
        this.queueTokenService = queueTokenService;
        this.configService = configService;
        this.namedLockService = namedLockService;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 100000)
    public void scheduleProcessQueue() {
        if (!scheduling) {
            return;
        }

        boolean lockAcquired = namedLockService.acquireLock("processing_queue", 10);
        if (!lockAcquired) {
            log.info("Another process is already handling the queue.");
            return;
        }

        try {
            log.info("Processing queue tokens...");
            int count = configService.getMaxQueueTokens();
            List<QueueToken> queueTokens = queueTokenService.getQueueTokens(count);
            queueTokenService.processQueueTokens(queueTokens);
        } finally {
            namedLockService.releaseLock("processing_queue");
        }
    }
}