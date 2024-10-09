# API 명세

### 1. 유저 토큰 생성 API

> ![](https://img.shields.io/static/v1?label=&message=POST&color=green) <br>
> /**users/token**

<details markdown="1">
<summary>유저 토큰 생성</summary>

#### Request
##### Body

|  name  | type | description | required |
|:------:|:----:|:-----------:| :---: |
| userId | long |   유저 식별값    | Required |

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
 {
    "token": "c282ee87-0c94-4a22-ac6a-151c2df634a3" (토큰)
}
  ```
  </details>
</details>
<br>

### 2. 대기 순서 조회 API

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue) <br>
> /**users/:userId/rank**

<details markdown="1">
<summary>대기 순서 조회</summary>

#### Request
##### Path Variable

|  name  | type | description | required |
|:------:|:----:|:-----------:| :---: |
| userId | long |   유저 식별값    | Required |

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
{
    "rank": 100 (대기 순서)
}
  ```
  </details>
</details>
<br>

### 3. 콘서트 목록 조회 API

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue) <br>
> /**concerts**

<details markdown="1">
<summary>콘서트 목록 조회</summary>

#### Request

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
[
    {
        "concertId": 1 (콘서트 식별값),
        "concertName": "아이유 가을 콘서트" (콘서트 이름),
    }
]
 ```
  </details>
</details>
<br>

### 4. 예약 가능 날짜 조회 API

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue) <br>
> /**concert-dates/:concertId**

<details markdown="1">
<summary>예약 가능 날짜 조회</summary>

#### Request
##### Path Variable

|   name    | type | description | required |
|:---------:|:----:|:-----------:| :---: |
| concertId | long |   콘서트 식별값   | Required |

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
[
    {
        "id": 1 (콘서트 날짜 식별값),
        "total_capacity": 50 (전체 좌석 수),
        "current_capacity": 30 (현재 예약된 좌석 수),
        "concert_date": "2024-10-09T15:10:46.092456 (콘서트 날짜)"
    }
] 
 ```
  </details>
</details>
<br>

### 5. 예약 가능 좌석 조회 API

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue) <br>
> /**concert-seats/:concertSeatId**

<details markdown="1">
<summary>예약 가능 좌석 조회</summary>

#### Request
##### Path Variable

|     name      | type | description | required |
|:-------------:|:----:|:-----------:| :---: |
| concertSeatId | long | 콘서트 날짜 식별값  | Required |

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
[
    {
        "seatId": 1 (좌석 식별값),
        "seatNumber": "AAS-1" (좌석 번호),
        "seatPrice": 100000 (좌석 가격)
    }
]
 ```
  </details>
</details>
<br>

### 6. 좌석 예약 API

> ![](https://img.shields.io/static/v1?label=&message=POST&color=green) <br>
> /**concert-seats/:concertSeatId**

<details markdown="1">
<summary>좌석 예약</summary>

#### Request
##### Body
```
{
  "userId":1 (유저 식별값),
  "seatId":1 (좌석 식별값),
}
```
#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
{
    "userId": 1 (유저 식별값), 
    "seatId": 1 (좌석 식별값),
    "reservationId": 1 (예약 식별값),
    "price": 100000 (좌석 가격),
    "status": "CONFIRMED (예약 상태)"
}
 ```
  </details>
</details>
<br>

### 7. 잔액 충전 API

> ![](https://img.shields.io/static/v1?label=&message=PATCH&color=yellow) <br>
> /**balances/**

<details markdown="1">
<summary>잔액 충전</summary>

#### Request
##### Body
```
{
    "userId":1 (유저 식별값),
    "amount":100 (충전 금액)
}
```
#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
{
    "balance": 100000 (잔액)
}
 ```
  </details>
</details>
<br>

### 8. 잔액 조회 API

> ![](https://img.shields.io/static/v1?label=&message=GET&color=blue) <br>
> /**users/:userId/balance**

<details markdown="1">
<summary>잔액 조회</summary>

#### Request
##### Path Variable

|  name  | type | description | required |
|:------:|:----:|:-----------:| :---: |
| userId | long |   유저 식별 값   | Required |

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
{
    "balance": 100000 (잔액)
}
 ```
  </details>
</details>
<br>


### 9. 결제 API

> ![](https://img.shields.io/static/v1?label=&message=POST&color=green) <br>
> /**payments**

<details markdown="1">
<summary>결제</summary>

#### Request
##### Body
```
{
    "reservationId":1
}
```

#### Response

  <details markdown="1">
  <summary>200 OK </summary>

  ```
{
    "paymentId": 1,
    "reservationId": 1,
    "amount": 100000,
    "status": "결제 완료"
}
 ```
  </details>
</details>
<br>