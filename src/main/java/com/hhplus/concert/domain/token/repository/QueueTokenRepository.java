package com.hhplus.concert.domain.token.repository;

import com.hhplus.concert.domain.token.model.QueueTokenStatus;
import com.hhplus.concert.domain.token.model.QueueToken;

import java.util.List;

public interface QueueTokenRepository {
    String token(String token, Long time);

    boolean findToken(String token);

    Long rank(String token);


    void processed(int count);
}
