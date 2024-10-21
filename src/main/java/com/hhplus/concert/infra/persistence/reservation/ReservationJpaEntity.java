package com.hhplus.concert.infra.persistence.reservation;

import com.hhplus.concert.domain.reservation.model.Reservation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Entity(name = "reservation")
public class ReservationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "price")
    private int price;

    @Column(name = "concert_date_seat_id")
    private Long concertDateSeatId;

    @Column(name = "reservation_date")
    private Long reservationDate;

    @Column(name = "expiration_date")
    private Long expirationDate;

    public ReservationJpaEntity(Long userId, int price, Long concertDateSeatId, Long reservationDate, Long expirationDate) {
        this.userId = userId;
        this.price = price;
        this.concertDateSeatId = concertDateSeatId;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
    }

    public static ReservationJpaEntity from(Reservation reservation) {
        return new ReservationJpaEntity(reservation.getUserId(), reservation.getPrice(), reservation.getConcertDateSeatId(), reservation.getReservationDate(), reservation.getExpirationDate());
    }


    public Reservation toDomain() {
        return Reservation.from(id, userId, price, concertDateSeatId, reservationDate, expirationDate);
    }
}
