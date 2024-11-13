# 🎶 콘서트 예약 최적화 가이드

이 가이드는 콘서트 예약 시스템에서의 쿼리 최적화 전략을 다룬다. 적절한 인덱스 사용과 쿼리 조건 최적화를 통해 콘서트 날짜 및 좌석 조회 성능을 크게 개선할 수 있다.

---

## 🎯 최적화 대상 조회 기능 요약

1. **예약 가능한 콘서트 날짜 조회**
    - 특정 콘서트의 예약 가능 날짜를 조회하여 불필요한 데이터 접근을 줄인다.
2. **예약 가능한 콘서트 날짜 좌석 조회**
    - 특정 콘서트 날짜의 좌석 상태를 필터링하여, 예약 가능한 좌석만 효율적으로 조회한다.
3. **좌석 선점 시 좌석 조회**
    - 기본 키를 활용하여 특정 좌석을 빠르게 조회하고, 추가적인 중복 인덱스를 방지한다.
4. **예약 시 예약 정보 조회**
    - 예약 아이디로 데이터를 빠르게 조회하고, 만료일 조건은 애플리케이션에서 검증하여 데이터베이스의 불필요한 조건 필터링을 줄인다.

---

## 📅 예약 관련 쿼리 및 최적화 전략

### 1. **예약 가능한 콘서트 날짜 조회**

```sql

SELECT
    cdje1_0.id,
    cdje1_0.concert_id,
    cdje1_0.current_capacity,
    cdje1_0.place,
    cdje1_0.start_date,
    cdje1_0.total_capacity
FROM
    concert_date cdje1_0
WHERE
    cdje1_0.concert_id = 1
  AND cdje1_0.current_capacity < cdje1_0.total_capacity;

```

- **🗝️ 추천 인덱스**: `(concert_id, current_capacity)`
- **💡 이유**:
    - 쿼리에서 `concert_id`와 `current_capacity`를 조건으로 필터링하고 있으므로, 이 두 컬럼을 포함한 복합 인덱스를 사용해 쿼리 성능을 최적화할 수 있다.
    - **데이터 분포도 분석**을 통해 `concert_id`가 다양한 값으로 분포되어 있어 선택도가 높고, 조회 범위를 효과적으로 좁힐 수 있으므로 인덱스의 첫 번째 위치에 배치한다.
    - `current_capacity`는 좌석 수와 관련된 보조 필터로서, `concert_id`에 따라 필터링된 데이터 내에서 추가 조건을 확인하는 데 유리하다.
- **🎛️ 데이터 개수 및 분포도 확인**:
    - **전체 데이터 개수**: 데이터 양을 확인하기 위해 전체 개수를 조회했으며, 이 쿼리의 결과로, `concert_date` 테이블에는 약 `10000000`개의 레코드가 있음을 확인했다. 이 정도 규모에서는 적절한 인덱스 사용을 통해 효율적인 조회 성능을 기대할 수 있다.

        ```sql
        
        SELECT count(*) FROM concert_date;
        
        ```

    - **concert_id 별 데이터 분포도**:결과적으로 `concert_id`는 약 100개의 데이터씩 고르게 분포되어 있어, 인덱스의 첫 번째 위치에 배치하는 것이 효과적임을 확인했다.

        ```sql
        
        SELECT concert_id, COUNT(*) as count
        FROM concert_date
        GROUP BY concert_id;
        
        ```

    - **current_capacity 별 분포도**:`current_capacity`도 값별로 약 20개씩 일정하게 분포되어 있음을 확인했으며, `concert_id`와 함께 복합 인덱스에 포함하기에 적합하다고 판단했다.

        ```sql
        
        SELECT current_capacity, COUNT(*) as count
        FROM concert_date
        GROUP BY current_capacity;
        
        ```

- **📈 기대 효과**:
    - 복합 인덱스 `(concert_id, current_capacity)`를 설정하여 데이터 필터링 범위를 크게 줄일 수 있어, 예약 가능한 콘서트 날짜를 빠르게 조회할 수 있다.
    - 테이블 스캔을 방지하고, **인덱스 범위 스캔(Index Range Scan)**을 통해 효율적으로 데이터를 검색할 수 있다.

### ⏳ 인덱스 적용 전후의 실행 계획 비교

1. **인덱스 적용 전**

    ```sql
    sql
    코드 복사
    EXPLAIN
    SELECT
        cdje1_0.id,
        cdje1_0.concert_id,
        cdje1_0.current_capacity,
        cdje1_0.place,
        cdje1_0.start_date,
        cdje1_0.total_capacity
    FROM
        concert_date cdje1_0
    WHERE
        cdje1_0.concert_id = 1
      AND cdje1_0.current_capacity < cdje1_0.total_capacity;
    
    ```

   | id | select_type | table | type | possible_keys | key | key_len | ref | rows | filtered | Extra |
       | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
   | 1 | SIMPLE | cdje1_0 | ALL | NULL | NULL | NULL | NULL | 1000 | 3.33 | Using where |
    - **type**: `ALL` - 테이블 전체를 스캔합니다. 이는 인덱스가 없기 때문에 발생하는 결과
    - **rows**: `1000` - 전체 1000개의 레코드를 스캔하여 조건에 맞는 데이터를 찾음
    - **filtered**: `3.33` - 조건에 맞는 데이터는 전체 중 약 3.33%만 해당합니다. 전체 테이블을 스캔하므로 비효율적
    - **Extra**: `Using where` - 조건이 WHERE 절에 지정된 대로 작동하지만, 전체 테이블 스캔으로 인해 비효율적
2. **인덱스 적용 후**

    ```sql
    sql
    코드 복사
    EXPLAIN
    SELECT
        cdje1_0.id,
        cdje1_0.concert_id,
        cdje1_0.current_capacity,
        cdje1_0.place,
        cdje1_0.start_date,
        cdje1_0.total_capacity
    FROM
        concert_date cdje1_0
    WHERE
        cdje1_0.concert_id = 1
      AND cdje1_0.current_capacity < cdje1_0.total_capacity;
    
    ```

   | id | select_type | table | type | possible_keys | key | key_len | ref | rows | filtered | Extra |
       | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
   | 1 | SIMPLE | cdje1_0 | ref | idx_concert_current_capacity | idx_concert_current_capacity | 9 | const | 100 | 33.33 | Using where |
    - **type**: `ref` - `concert_id` 조건을 활용해 인덱스를 통해서만 일부 데이터를 조회
    - **possible_keys**: `idx_concert_current_capacity` - `concert_id`와 `current_capacity`로 구성된 인덱스를 사용
    - **rows**: `100` - 1000개에서 약 100개의 데이터만 조회합니다. 불필요한 데이터 스캔이 줄어들어 쿼리 효율이 증가
    - **filtered**: `33.33` - 인덱스를 사용하여 조건을 만족하는 데이터가 33.33%로, 더 효과적으로 필터링

---

### 🔍 인덱스 적용에 따른 성능 향상

- **전체 테이블 스캔에서 인덱스 사용으로 변경**: `ALL`에서 `ref`로 변경됨에 따라 **불필요한 데이터 스캔이 크게 줄어들고** 필요한 데이터만 인덱스를 통해 빠르게 조회할 수 있게 되었다.
- **행 개수 감소**: 전체 테이블(1000행)을 모두 스캔하던 것이 100행으로 줄어, 데이터베이스 접근 횟수가 감소하고 조회 성능이 향상되었다.
- **필터링 효과 증가**: 인덱스 적용 후 `filtered`가 3.33에서 33.33으로 높아져, 인덱스를 통한 조건 필터링이 더 효율적으로 동작했다.

인덱스를 적용한 결과, **데이터 스캔 범위가 크게 감소하고 필터링 효율이 증가하여** 쿼리 성능이 크게 향상되었다.

---

### 2. **예약 가능한 콘서트 날짜 좌석 조회**

```sql

SELECT
    cdsje1_0.id,
    cdsje1_0.concert_date_id,
    cdsje1_0.price,
    cdsje1_0.status
FROM
    concert_date_seat cdsje1_0
WHERE
    cdsje1_0.concert_date_id = 1
  AND cdsje1_0.status <> 'AVAILABLE';

```

- **🗝️ 추천 인덱스**: `(concert_date_id, status)`
- **💡 이유**:
    - 쿼리 조건에서 `concert_date_id`와 `status`를 필터링하고 있으므로, 이 두 컬럼을 결합한 복합 인덱스를 설정하여 성능을 최적화할 수 있다.
    - `concert_date_id`는 선택도가 높고, 해당 콘서트 날짜의 좌석만 빠르게 조회하도록 인덱스의 첫 번째 위치에 둔다.
    - `status`는 `AVAILABLE`과 `RESERVED`로 분포된 값으로, 두 값 중 `AVAILABLE`이 아닌 경우를 찾기 위한 추가 필터 역할을 수행한다.
- **🎛️ 데이터 개수 및 분포도 확인**:
    - **전체 데이터 개수**: 테이블에 저장된 데이터의 전체 개수를 확인하여 데이터 규모를 파악

        ```sql
        
        SELECT count(*) FROM concert_date_seat;
        
        ```

      `concert_date_seat` 테이블에는 **총 10,000,000건**의 데이터가 존재하여, 대량의 데이터를 효율적으로 필터링하는 것이 중요하다.

    - **status 컬럼의 분포도 확인**:

        ```sql
        
        SELECT status, COUNT(*) as count
        FROM concert_date_seat
        GROUP BY status;
        
        ```

        - 결과: `AVAILABLE` 5,000,000건, `RESERVED` 5,000,000건
        - `status` 컬럼은 `AVAILABLE`과 `RESERVED` 값이 50:50으로 균등하게 분포되어 있어, **비교적 낮은 선택도**를 가진다. 이로 인해 단일 인덱스 효과는 제한적일 수 있지만, `concert_date_id`와 결합하면 필터링 성능이 개선될 수 있다.
    - **concert_date_id와 status 결합 분포도**:

        ```sql
        
        SELECT concert_date_id, COUNT(*) as count
        FROM concert_date_seat
        GROUP BY concert_date_id;
        
        ```

        - 각 `concert_date_id` 값별로 **10,000건씩 균등하게 분포**되어 있음을 확인
        - `concert_date_id`가 다양하게 분포되어 있어 선택도가 높고, 필터링 범위를 좁힐 수 있으므로 인덱스의 첫 번째 위치에 배치하기에 적합하다.
- **📈 기대 효과**:
    - 복합 인덱스 `(concert_date_id, status)`를 사용하여 `concert_date_id`와 `status` 조건으로 대량의 데이터를 효율적으로 필터링할 수 있다.
    - 인덱스를 통해 **불필요한 데이터 스캔을 최소화**하고, 빠르게 `AVAILABLE`이 아닌 좌석을 조회할 수 있어 성능을 향상시킨다.

---

### 3. **좌석 선점 시 좌석 조회**

```sql

 select
        cdsje1_0.id,
        cdsje1_0.concert_date_id,
        cdsje1_0.price,
        cdsje1_0.status 
    from
        concert_date_seat cdsje1_0 
    where
        cdsje1_0.concert_date_id=? 
        and cdsje1_0.id=? 
        and cdsje1_0.status=?
```

- **🗝️ 기존 인덱스**:
    - 이 프로젝트에서는 이미 `(concert_date_id, status)`에 대한 복합 인덱스가 존재하여, `concert_date_id`와 `status`를 조건으로 사용하는 쿼리에 대해 충분한 최적화가 이루어져 있다.
- **📌 추가 복합 인덱스를 설정하지 않은 이유**:
    - **기본 키 활용**: `concertDateSeatId`는 기본 키(`id`)로 설정되어 있으므로, 단일 인덱스를 통해 빠른 조회가 가능합니다. 별도의 복합 인덱스를 설정하지 않아도 기본 키 인덱스를 활용해 효율적으로 조회할 수 있다.
    - **중복 인덱스 방지**: 이미 `(concert_date_id, status)`로 최적화된 복합 인덱스가 존재하여, **별도로 `(id, concert_date_id, status)`와 같은 추가 인덱스를 생성할 경우 중복 인덱스가 생긴다**. 이로 인해 인덱스 유지 비용이 증가하게 되므로 불필요한 인덱스 생성을 피하는 것이 좋다.
    - **기존 인덱스 활용 가능**: `concert_date_id`와 `status`에 대한 기존 인덱스가 이미 최적화된 구조를 제공하므로, 추가적인 인덱스를 설정하지 않아도 조회 성능이 충분히 보장된다.
- **📈 기대 효과**:
    - 불필요한 인덱스를 추가하지 않고도 기본 키와 기존 복합 인덱스를 통해 충분한 성능 최적화를 달성할 수 있다.


---

### 4. **예약 시 예약 정보 조회**

```sql
    select
        rje1_0.id,
        rje1_0.concert_date_seat_id,
        rje1_0.expiration_date,
        rje1_0.price,
        rje1_0.reservation_date,
        rje1_0.user_id 
    from
        reservation rje1_0 
    where
        rje1_0.id=? 
        and rje1_0.expiration_date>?
```

- **🏷️ 최적화 제안**: `id`로만 조회하고 만료일 조건은 애플리케이션 코드에서 검증
- **💡 이유**:
    - `reservationId`는 기본 키(`id`)이므로, 별도의 인덱스 없이도 빠르게 조회할 수 있다.
    - `expiration_date` 조건은 애플리케이션 코드에서 검증하여 데이터베이스에서 불필요한 조건 검색을 줄이는 방식이 더 효율적.
- **📈 기대 효과**:
    - 기본 키로 빠르게 조회하고 애플리케이션 코드에서 만료 여부를 검증하여 데이터베이스 작업을 최적화한다.

---

## 📝 결론

- **인덱스 사용 쿼리**: 예약 가능한 콘서트 날짜 조회, 예약 가능한 콘서트 날짜 좌석 조회
    - 이 두 쿼리는 인덱스를 통해 필터링 범위를 좁혀 쿼리 성능을 개선할 수 있다.
- **애플리케이션 검증 쿼리**: 예약 시 만료되지 않은 예약 정보 조회
    - 기본 키로 빠르게 조회 가능한 경우는 인덱스를 추가하지 않고 애플리케이션 코드에서 필요한 상태와 만료일 검증을 수행하여 데이터베이스의 관리 오버헤드를 줄이는 것이 더 효율적이다.