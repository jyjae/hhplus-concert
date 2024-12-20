package com.hhplus.concert.interfaces.api.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.ErrorCode;
import com.hhplus.concert.interfaces.api.error.ErrorResponse;
import com.hhplus.concert.interfaces.api.reservation.dto.ReservationRequest;
import com.hhplus.concert.interfaces.api.reservation.dto.ReservationResponse;
import com.hhplus.concert.util.UuidUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReservationControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private QueueTokenService queueTokenService;

  private RestTemplate restTemplate = new RestTemplate();

  private String baseUrl(String uri) {
    return "http://localhost:"+port+uri;
  }


  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("예약 성공")
  @Test
  void shouldReservationSuccessfully() {
    String token = queueTokenService.createQueueToken(new CreateQueueTokenCommand(1L, UuidUtil.generateUuid()));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", token);

    ReservationRequest request = new ReservationRequest(1L, 1L, 1L, 9L);
    HttpEntity<ReservationRequest> requestEntity = new HttpEntity(request, headers);

    ResponseEntity<ReservationResponse> response = restTemplate.exchange(
        baseUrl("/reservations"),
        HttpMethod.POST,
        requestEntity,
        ReservationResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();

    ReservationResponse reservationResponse = response.getBody();
    assertThat(reservationResponse.getReservationId()).isEqualTo(5L);

  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 예약 실패")
  @Test
  void shouldReservationFail() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", UuidUtil.generateUuid());

    ReservationRequest request = new ReservationRequest(1L, 1L, 1L, 9L);
    HttpEntity<ReservationRequest> requestEntity = new HttpEntity(request, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        baseUrl("/reservations"),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(404);
    assertThat(response.getBody().message()).isEqualTo("Token not found");


  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 예약 실패")
  @Test
  void shouldFailReservationTokenNull() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    ReservationRequest request = new ReservationRequest(1L, 1L, 1L, 9L);
    HttpEntity<ReservationRequest> requestEntity = new HttpEntity(request, headers);

   ResponseEntity<ErrorResponse> response =  restTemplate.exchange(
        baseUrl("/reservations"),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.NOT_FOUND_TOKEN.getMessage());

  }


}