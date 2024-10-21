package com.hhplus.concert.domain.token.service;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.token.model.QueueTokenStatus;
import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.dto.GetUserQueueRankQuery;
import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.repository.QueueTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueueTokenService {

    private final QueueTokenRepository tokenRepository;
    private final TimeProvider timeProvider;


    @Transactional
    public String createQueueToken(CreateQueueTokenCommand command) {
        return tokenRepository.token(command.getUserId(), command.getToken(), timeProvider.getCurrentInstantPlusMinutes(30));
    }

    @Transactional(readOnly = true)
    public QueueToken getQueueToken(GetQueueTokenCommand command) {
        return tokenRepository.getToken(command.getUserId());
    }

    @Transactional(readOnly = true)
    public QueueToken findQueueToken(FindQueueTokenQuery command) {
        return tokenRepository.findToken(command.getToken(), timeProvider.getCurrentTimestamp());
    }

    @Transactional(readOnly = true)
    public Long userRank(GetUserQueueRankQuery command) {
        // 대기순서 공식: 내 id - 마지막 processing id - n
        long lastProcessingId = tokenRepository.lastProcessingToken(QueueTokenStatus.PROCESSING);
        return getRank(command.getTokenId(), command.getCapacity(), lastProcessingId);
    }

    @Transactional(readOnly = true)
    public List<QueueToken> getQueueTokens(Integer count) {
        return tokenRepository.getTokens(count);
    }

    @Transactional
    public void deleteQueueToken(String token) {
        tokenRepository.delete(token);
    }

    public void processQueueTokens(List<QueueToken> queueTokens) {
        queueTokens.forEach(token -> {
            tokenRepository.processed(token.processed());
        });
    }

    private Long getRank(Long tokenId, Integer count, Long lastProcessingId) {
        return tokenId - lastProcessingId - count < 0 ? 0 : tokenId - lastProcessingId - count;
    }

}
