package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeat;
import com.hhplus.concert.domain.concert.concertdateseat.ConcertDateSeatStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity(name = "concert_date_seat")
public class ConcertDateSeatJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concert_date_id")
    private Long concertDateId;

    @Column(name = "price")
    private int price;

    @Column(name = "expired_date")
    private Long expiredDate;

    @Column(name = "status")
    private String status;

    public ConcertDateSeat toDomain() {
        return ConcertDateSeat.of(concertDateId, id, price, expiredDate, ConcertDateSeatStatus.valueOf(status));
    }
}
