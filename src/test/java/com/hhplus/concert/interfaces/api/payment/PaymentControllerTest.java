package com.hhplus.concert.interfaces.api.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.InvalidException;
import com.hhplus.concert.exception.NotFoundException;
import com.hhplus.concert.interfaces.api.error.ErrorResponse;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentRequest;
import com.hhplus.concert.interfaces.api.payment.dto.CreatePaymentResponse;
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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private QueueTokenService queueTokenService;

  private final RestTemplate restTemplate = new RestTemplate();

  private String baseUrl(String uri) {
    return "http://localhost:"+port+uri;
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("결제 성공")
  @Test
  void shouldCreatePaymentSuccessfully() {
    String token = queueTokenService.createQueueToken(new CreateQueueTokenCommand(1L, UuidUtil.generateUuid()));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", token);

    CreatePaymentRequest request = new CreatePaymentRequest(1L, 1L);
    HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<CreatePaymentResponse> response = restTemplate.exchange(
        baseUrl("/payments"),
        HttpMethod.POST,
        requestEntity,
        CreatePaymentResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();

    CreatePaymentResponse paymentResponse = response.getBody();
    assertThat(paymentResponse.getPaymentId()).isEqualTo(1L);
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 결제 실패")
  @Test
  void shouldCreatePaymentFail() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", UuidUtil.generateUuid());

    CreatePaymentRequest request = new CreatePaymentRequest(1L, 1L);
    HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
              baseUrl("/payments"),
              HttpMethod.POST,
              requestEntity,
              ErrorResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(404);
    assertThat(response.getBody().message()).isEqualTo("Token not found");
  }



  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("토큰 Null 값으로 결제 실패")
  @Test
  void shouldFailCreatePaymentToTokenNull() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    CreatePaymentRequest request = new CreatePaymentRequest(1L, 1L);
    HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        baseUrl("/payments"),
        HttpMethod.POST,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(400);
  }

}