package com.hhplus.concert.infra.persistence.concert.concertdate;

import com.hhplus.concert.domain.concert.model.ConcertDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity(name = "concert_date")
public class ConcertDateJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "concert_id")
  private Long concertId;

  @Column(name = "total_capacity")
  private Integer totalCapacity;

  @Column(name = "current_capacity")
  private Integer currentCapacity;

  @Column(name = "place")
  private String place;

  @Column(name = "start_date")
  private Long startDate;

  public ConcertDateJpaEntity(Long concertId, Integer totalCapacity, Integer currentCapacity,
      String place, Long startDate) {
    this.concertId = concertId;
    this.totalCapacity = totalCapacity;
    this.currentCapacity = currentCapacity;
    this.place = place;
    this.startDate = startDate;
  }

  public static ConcertDate toDomain(ConcertDateJpaEntity concertDate) {
    return ConcertDate.of(concertDate.id, concertDate.concertId, concertDate.totalCapacity,
        concertDate.currentCapacity, concertDate.startDate, concertDate.place);
  }

    public static ConcertDateJpaEntity of(Long concertId, int totalCapacity, int currentCapacity, String place, Long startDate) {
        return new ConcertDateJpaEntity(concertId, totalCapacity, currentCapacity, place, startDate);
    }
}
