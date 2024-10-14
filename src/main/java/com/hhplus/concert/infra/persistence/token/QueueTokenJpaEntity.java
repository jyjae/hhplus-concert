package com.hhplus.concert.infra.persistence.token;

import com.hhplus.concert.domain.token.QueueToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @CreationTimestamp
    private long createdAt;

    @Column(name = "status")
    private String status;


    public QueueTokenJpaEntity(Long userId, String token, String status) {
        this.userId = userId;
        this.token = token;
        this.status = status;
    }


    public static QueueTokenJpaEntity of(Long userId, String token, String status) {
        return new QueueTokenJpaEntity(userId, token, status);
    }

    public static QueueTokenJpaEntity of(QueueToken queueToken) {
        return new QueueTokenJpaEntity(queueToken.getUserId(), queueToken.getToken(), queueToken.getStatus().getValue());
    }

}
