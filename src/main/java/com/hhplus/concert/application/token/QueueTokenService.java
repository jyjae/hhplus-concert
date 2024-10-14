package com.hhplus.concert.application.token;

import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.domain.token.QueueTokenRepository;
import com.hhplus.concert.domain.token.QueueTokenStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QueueTokenService {

    private final QueueTokenRepository tokenRepository;

    public QueueTokenService(QueueTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public String createQueueToken(CreateQueueTokenCommand command) {
        return tokenRepository.token(command.userId(), command.token());
    }

    @Transactional(readOnly = true)
    public QueueToken getQueueToken(GetQueueTokenCommand command) {
        return tokenRepository.getToken(command.userId());
    }

    @Transactional(readOnly = true)
    public QueueToken findQueueToken(FindQueueTokenCommand command) {
        return tokenRepository.findToken(command.userId(), command.token());
    }

    @Transactional(readOnly = true)
    public Long userRank(GetUserQueueRankQuery command) {
        // 대기순서 공식: 내 id - 마지막 processing id - n
        Long lastProcessingId = tokenRepository.lastProcessingToken(QueueTokenStatus.PROCESSING);
        return getRank(command.getTokenId(), command.getCapacity(), lastProcessingId);
    }

    @Transactional
    public List<QueueToken> getQueueTokens(Integer count) {
        return tokenRepository.getTokens(count);
    }

    public void processQueueTokens(List<QueueToken> queueTokens) {
        queueTokens.forEach(token -> {
            token.processed();
            tokenRepository.save(token);
        });
    }

    private Long getRank(Long tokenId, Integer count, Long lastProcessingId) {
        return tokenId - lastProcessingId - count < 0 ? 1 : tokenId - lastProcessingId - count + 1;
    }


}
