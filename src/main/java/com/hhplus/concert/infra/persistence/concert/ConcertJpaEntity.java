package com.hhplus.concert.infra.persistence.concert;

import com.hhplus.concert.domain.concert.Concert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity
@Getter
public class ConcertJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "start_date")
  private Long startDate;

  @Column(name = "end_date")
  private Long endDate;


  public ConcertJpaEntity(String name, Long startDate, Long endDate) {
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }


  public Concert toDomain() {
    return Concert.of(id, name, startDate, endDate);
  }
}
