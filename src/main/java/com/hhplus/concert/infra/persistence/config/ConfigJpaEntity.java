package com.hhplus.concert.infra.persistence.config;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "config")
@Entity
public class ConfigJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_key", unique = true, nullable = false)
    private String key;

    @Getter
    @Column(name = "config_value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "updated_at")
    private long updatedAt;

    public ConfigJpaEntity(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

}
