-- Config 데이터 삽입
INSERT INTO config (config_key, config_value, updated_at) VALUES ('max-processing-token', '100', 1798642845000);

-- User 데이터 삽입
INSERT INTO "user" (id, name) VALUES (1, 'Alice');
INSERT INTO "user" (id, name) VALUES (2, 'Bob');
INSERT INTO "user" (id, name) VALUES (3, 'Charlie');

INSERT INTO point (user_id, point, last_updated, version) values (1L, 1200000, 1798642845000, 0);


-- QueueToken 데이터 삽입 (UUID를 token으로 사용)
INSERT INTO queue_token (user_id, token, status, created_at, expired_date)
VALUES (1, UUID(), 'PROCESSING', 1798642845000, 1798644645000);

INSERT INTO queue_token (user_id, token, status, created_at, expired_date)
VALUES (2, UUID(), 'PROCESSING', 1798642845000, 1798644645000);

INSERT INTO queue_token (user_id, token, status, created_at, expired_date)
VALUES (3, UUID(), 'PROCESSING', 1798642845000, 1798644645000);


-- Concert 데이터 삽입
INSERT INTO concert (id, name, start_date, end_date)
VALUES (1, 'Spring Festival', 1697594400000, 1697680800000);

INSERT INTO concert (id, name, start_date, end_date)
VALUES (2, 'Summer Fest', 1697680800000, 1697767200000);


-- ConcertDate 데이터 삽입
INSERT INTO concert_date (id, concert_id, total_capacity, current_capacity, place, start_date)
VALUES (1, 1, 50, 0, 'Main Hall', 1697594400000);

INSERT INTO concert_date (id, concert_id, total_capacity, current_capacity, place, start_date)
VALUES (2, 1, 50, 10, 'Small Hall', 1697680800000);

INSERT INTO concert_date (id, concert_id, total_capacity, current_capacity, place, start_date)
VALUES (3, 2, 50, 50, 'Open Arena', 1697767200000);


-- ConcertDate ID: 1에 대한 50개 좌석 데이터 삽입
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5001 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 50000, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5003, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 60000 ,'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5005, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 70000, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5007, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5008, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5009, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5010, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5001, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5002, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5003, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5004, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5005, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5006, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5007, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5008, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5009, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5010, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5001, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5002, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5003, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5004, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5005, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5006, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5007, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5008, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5009, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5010, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5001, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5002, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5003, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5004, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5005, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5006, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5007, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5008, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5009, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5010, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5001, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5002, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5003, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5004, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5005, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5006, 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5007, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 50000 'RESERVED');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5009, 'AVAILABLE');
INSERT INTO concert_date_seat (concert_date_id, price, expired_date, status) VALUES (1, 5010, 'RESERVED');

-- 초기 예약 데이터 삽입
INSERT INTO reservation (user_id, price, concert_date_seat_id, reservation_date, expiration_date)
VALUES (1L, 10000, 1L, 1798644645000, 1798644345000);

INSERT INTO reservation ( user_id, price, concert_date_seat_id, reservation_date, expiration_date)
VALUES (1L, 10000, 4L, 1697594400000, 1697680800000);

INSERT INTO reservation (user_id, price, concert_date_seat_id, reservation_date, expiration_date)
VALUES (1L, 10000, 6L, 1697594400000, 1697680800000);