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
        return tokenRepository.token(command.getToken(), timeProvider.getCurrentInstantPlusMinutes(30));
    }

//    @Transactional(readOnly = true)
//    public QueueToken getQueueToken(GetQueueTokenCommand command) {
//        return tokenRepository.getToken(command.getUserId());
//    }

    @Transactional(readOnly = true)
    public boolean findQueueToken(FindQueueTokenQuery command) {
        return tokenRepository.findToken(command.getToken());
    }

    @Transactional(readOnly = true)
    public Long userRank(GetUserQueueRankQuery command) {
        return tokenRepository.rank(command.getToken());
    }

//    @Transactional(readOnly = true)
//    public List<QueueToken> getQueueTokens(Integer count) {
//        return tokenRepository.getTokens(count);
//    }
//
//    @Transactional
//    public void deleteQueueToken(String token) {
//        tokenRepository.delete(token);
//    }

    public void processQueueTokens(int count) {
        tokenRepository.processed(count);
    }


}
