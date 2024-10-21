package com.hhplus.concert.domain.token.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class QueueToken {

  private final Long id;
  private final Long userId;
  private final String token;
  private final long createdAt;
  private final QueueTokenStatus status;
  private final Long expiredDate;

  public static QueueToken of(
      Long id,
      Long userId,
      String token,
      long createdAt,
      QueueTokenStatus status,
      Long expiredDate
  ) {
    return QueueToken.builder()
        .id(id)
        .userId(userId)
        .token(token)
        .createdAt(createdAt)
        .status(status)
        .expiredDate(expiredDate)
        .build();
  }

  public QueueToken processed() {
    return QueueToken.builder()
        .id(id)
        .userId(userId)
        .token(token)
        .createdAt(createdAt)
        .status(QueueTokenStatus.PROCESSING)
        .expiredDate(expiredDate)
        .build();
  }
}
