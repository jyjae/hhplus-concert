package com.hhplus.concert.domain.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
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
    return new QueueToken(id, userId, token, createdAt, status, expiredDate);
  }

  public QueueToken processed() {
    return new QueueToken(this.id, this.userId, this.token, this.createdAt, QueueTokenStatus.PROCESSING, this.expiredDate);
  }
}
