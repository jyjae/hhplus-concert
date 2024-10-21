package com.hhplus.concert.domain.concert.repository;

import com.hhplus.concert.domain.concert.model.Concert;
import java.util.List;

public interface ConcertRepository {

  List<Concert> concerts();
}
