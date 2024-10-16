package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.domain.token.QueueToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "queue_token")
@Getter
public class QueueTokenJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private long createdAt;

    @Column(name = "status")
    private String status;

    @Column(name = "expired_date")
    private long expiredDate;


    public QueueTokenJpaEntity(Long userId, String token, String status, long expiredDate) {
        this.userId = userId;
        this.token = token;
        this.status = status;
        this.createdAt = Instant.now().toEpochMilli();
        this.expiredDate = expiredDate;
    }

    public QueueTokenJpaEntity(Long id, Long userId, String token, String status, long expiredDate) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.status = status;
        this.createdAt = Instant.now().toEpochMilli();
        this.expiredDate = expiredDate;
    }


    public static QueueTokenJpaEntity of(Long userId, String token, String status, long expiredDate) {
        return new QueueTokenJpaEntity(userId, token, status, expiredDate);
    }

    public static QueueTokenJpaEntity of(QueueToken queueToken) {
        return new QueueTokenJpaEntity(queueToken.getId(), queueToken.getUserId(), queueToken.getToken(), queueToken.getStatus().getValue(),
            queueToken.getExpiredDate());
    }

}
