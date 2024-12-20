//package com.hhplus.concert.infra.persistence.token;
//
//import com.hhplus.concert.domain.token.model.QueueToken;
//import com.hhplus.concert.domain.token.repository.QueueTokenRepository;
//import com.hhplus.concert.domain.token.model.QueueTokenStatus;
//import com.hhplus.concert.exception.ErrorType;
//import com.hhplus.concert.exception.NotFoundException;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//@RequiredArgsConstructor
//public class QueueTokenRepositoryImpl implements QueueTokenRepository {
//
//    private final QueueTokenJpaRepository tokenJpaRepository;
//    private final QueueTokenMapper queueTokenMapper;
//
//    @Override
//    public String token(Long userId, String token, Long time) {
//        return tokenJpaRepository.save(QueueTokenJpaEntity.of(userId, token, QueueTokenStatus.WAITING.getValue(), time)).getToken();
//    }
//
//    @Override
//    public QueueToken getToken(Long userId) {
//        return queueTokenMapper.mapToDomain(tokenJpaRepository.findTokenByUserId(userId));
//    }
//
//    @Override
//    public QueueToken findToken(String token, Long currentTime) {
//        return queueTokenMapper.mapToDomainOptional(tokenJpaRepository.findTokenByToken(token, currentTime))
//                .orElseThrow(() ->  new NotFoundException(ErrorType.NOT_FOUND_TOKEN));
//    }
//
//    @Override
//    public long lastProcessingToken(QueueTokenStatus queueTokenStatus) {
//        return tokenJpaRepository.findLastProcessingToken(queueTokenStatus.getValue());
//    }
//
//
//    @Override
//    public List<QueueToken> getTokens(Integer count) {
//        return tokenJpaRepository.findAllTokenAsc(count).stream()
//                .map(queueTokenMapper::mapToDomain)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void save(QueueToken token) {
//        tokenJpaRepository.save(QueueTokenJpaEntity.of(token));
//    }
//
//    @Override
//    public void processed(QueueToken queueToken) {
//        QueueTokenJpaEntity queueTokenJpaEntity = queueTokenMapper.mapToEntity(queueToken);
//        tokenJpaRepository.save(queueTokenJpaEntity);
//    }
//
//    @Override
//    public void delete(String token) {
//        tokenJpaRepository.deleteByToken(token);
//    }
//}
