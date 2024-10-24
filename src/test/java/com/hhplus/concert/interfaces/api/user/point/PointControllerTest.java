package com.hhplus.concert.interfaces.api.user.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.ErrorCode;
import com.hhplus.concert.interfaces.api.error.ErrorResponse;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointRequest;
import com.hhplus.concert.interfaces.api.user.point.dto.ChargePointResponse;
import com.hhplus.concert.util.UuidUtil;;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PointControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private QueueTokenService queueTokenService;

  private final RestTemplate restTemplate = new RestTemplate();


  private String baseUrl(String uri) {
    return "http://localhost:" + port + uri;
  }

  @BeforeEach
  public void setup() {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("잔액 충전 성공")
  @Test
  void shouldChargeSuccessfully() {
    // Given
    String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(2L)).getToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", token);

    ChargePointRequest request = new ChargePointRequest(1000);
    HttpEntity<ChargePointRequest> requestEntity = new HttpEntity<>(request, headers);

    // When
    ResponseEntity<ChargePointResponse> response = restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.PATCH,
        requestEntity,
        ChargePointResponse.class
    );

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getPointId()).isEqualTo(2L);
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 잔액 충전 실패")
  @Test
  void shouldFailChargeWithInvalidToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", UuidUtil.generateUuid());

    ChargePointRequest request = new ChargePointRequest(1000);
    HttpEntity<ChargePointRequest> requestEntity = new HttpEntity<>(request, headers);

    // When
    ResponseEntity<ErrorResponse> response =  restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.PATCH,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.NOT_FOUND_TOKEN.getMessage());
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 잔액 충전 실패")
  @Test
  void shouldFailChargeWithoutToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    ChargePointRequest request = new ChargePointRequest(1000);
    HttpEntity<ChargePointRequest> requestEntity = new HttpEntity<>(request, headers);

    // When
    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.PATCH,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.INVALID_PARAMETER.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.INVALID_TOKEN.getMessage());
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("잔액 조회 성공")
  @Test
  void shouldGetPointSuccessfully() {
    // Given
    String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(2L)).getToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", token);

    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    // When
    ResponseEntity<Integer> response = restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.GET,
        requestEntity,
        Integer.class
    );

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody()).isGreaterThanOrEqualTo(0);
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 잔액 충전 실패")
  @Test
  void shouldFailGetPointsWithInvalidToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", UuidUtil.generateUuid());

    ChargePointRequest request = new ChargePointRequest(1000);
    HttpEntity<ChargePointRequest> requestEntity = new HttpEntity<>(request, headers);

    // When
    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.GET,
        requestEntity,
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.NOT_FOUND_TOKEN.getMessage());
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 잔액 충전 실패")
  @Test
  void shouldFailGetPointsWithoutToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    ChargePointRequest request = new ChargePointRequest(1000);
    HttpEntity<ChargePointRequest> requestEntity = new HttpEntity<>(request, headers);

    // When
   ResponseEntity<ErrorResponse> response =  restTemplate.exchange(
        baseUrl("/users/" + 2 + "/points"),
        HttpMethod.GET,
        requestEntity,
       ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.INVALID_PARAMETER.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.INVALID_TOKEN.getMessage());
  }


}