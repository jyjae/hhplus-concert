package com.hhplus.concert.infra.persistence.concert.concertdateseat;

import com.hhplus.concert.domain.concert.model.ConcertDateSeat;
import com.hhplus.concert.domain.concert.constants.ConcertDateSeatStatus;
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

    @Column(name = "status")
    private String status;

//    @Version
//    private Long version = 0L;

    public ConcertDateSeatJpaEntity(Long concertDateId, int price, String status) {
        this.concertDateId = concertDateId;
        this.price = price;
        this.status = status;
    }

    public ConcertDateSeatJpaEntity(Long id, Long concertDateId, int price, String status) {
        this.id = id;
        this.concertDateId = concertDateId;
        this.price = price;
        this.status = status;
    }

    public static ConcertDateSeatJpaEntity of(Long concertDateId, int price, String status) {
        return new ConcertDateSeatJpaEntity(concertDateId, price,status);
    }

    public static ConcertDateSeatJpaEntity from(ConcertDateSeat concertDateSeat) {
        return new ConcertDateSeatJpaEntity(
                concertDateSeat.getId(),
                concertDateSeat.getConcertDateId(),
                concertDateSeat.getPrice(),
                concertDateSeat.getStatus().name());
    }


    public ConcertDateSeat toDomain() {
        return ConcertDateSeat.of(id, concertDateId, price, ConcertDateSeatStatus.valueOf(status));
    }

    public void update(ConcertDateSeat concertDateSeat) {
        this.concertDateId = concertDateSeat.getConcertDateId();
        this.price = concertDateSeat.getPrice();
        this.status = concertDateSeat.getStatus().name();
    }
}
