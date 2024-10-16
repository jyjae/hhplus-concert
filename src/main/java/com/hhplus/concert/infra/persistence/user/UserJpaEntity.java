package com.hhplus.concert.infra.persistence.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity(name = "`user`")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public UserJpaEntity(String name) {
        this.name = name;
    }

    public static UserJpaEntity of(String name) {
        return new UserJpaEntity(name);
    }
}
