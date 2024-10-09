# PROJECT_GUIDE

## 패키지 구조
```bash
src/main/java
└── com
    └── hhplus
        └── concert
            ├── application
            ├── domain
            ├── exception
            ├── infra
            └── interfaces
                └── api
                    ├── balance
                    ├── concert
                    ├── concertdate
                    ├── payment
                    ├── queue
                    ├── reservation
                    ├── seat
                    └── user
```

## 기술 스택
- **Java 17**
- **Spring Boot**
    - Data JPA
    - Cache
- **H2 데이터베이스** (개발 및 테스트 환경에서 인메모리 데이터베이스 사용)
- **낙관적 락(Optimistic Locking)**

## 캐싱 전략
- 자주 액세스되는 콘서트 데이터와 같은 부분에 캐싱을 적용하여 성능을 최적화하였습니다. 캐시 설정은 Spring Boot Cache 기능을 활용하여 구현되었습니다.

## 낙관적 락(Optimistic Locking) 사용
- 동시 수정 시 업데이트 손실을 방지하기 위해 낙관적 락을 사용합니다. 이를 통해 여러 사용자가 동일한 데이터를 수정할 때, 마지막 수정만 저장되는 문제가 발생하지 않도록 합니다.
- 또한, 대기열 시스템에서 순서를 보장해주기 때문에 비관적 락(Pessimistic Lock)을 사용할 필요가 없으며, 대신 성능과 확장성을 고려하여 낙관적 락을 선택하였습니다.

## 테스트
- **단위 테스트** 작성하여 각 기능을 검증합니다. 테스트 도구로는 JUnit과 MockMvc를 사용합니다.

