package com.hhplus.concert.domain.concert.concertdateseat;

import java.util.List;

public interface ConcertDateSeatRepository {
    List<ConcertDateSeat> concertDateSeat(long concertDateId, long currentTime);
}
