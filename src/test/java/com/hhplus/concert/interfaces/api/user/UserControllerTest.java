package com.hhplus.concert.interfaces.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.concert.AbstractRedisTest;
import com.hhplus.concert.domain.token.dto.CreateQueueTokenCommand;
import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.ErrorCode;
import com.hhplus.concert.interfaces.api.error.ErrorResponse;
import com.hhplus.concert.interfaces.api.user.dto.GetQueueResponse;
import com.hhplus.concert.util.UuidUtil;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest extends AbstractRedisTest {

  @LocalServerPort
  private int port;

  @Autowired
  private QueueTokenService queueTokenService;

  private RestTemplate restTemplate = new RestTemplate();


  private String baseUrl(String uri) {
    return "http://localhost:" + port + uri;
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("대기 순서 조회 성공")
  @Test
  void shouldGetQueueRankSuccessfully() {
    // Given
    String token = queueTokenService.createQueueToken(new CreateQueueTokenCommand(1L, UuidUtil.generateUuid()));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", token);

    // When
    ResponseEntity<GetQueueResponse> response = restTemplate.exchange(
            baseUrl("/users/2/rank"),
            HttpMethod.GET,
            new HttpEntity<>(headers),
            GetQueueResponse.class
    );
    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getRank()).isEqualTo(1L);
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 대기 순서 조회 실패")
  @Test
  void shouldFailGetQueueRankWithInvalidToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("X-Access-Token", UuidUtil.generateUuid());

    // When
    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        baseUrl("/users/2/rank"),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.NOT_FOUND_TOKEN.getMessage());
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("토큰 없이 대기 순서 조회 실패")
  @Test
  void shouldFailGetQueueRankWithNullToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // When
    ResponseEntity<ErrorResponse> response =  restTemplate.exchange(
        baseUrl("/users/"+2+"/rank"),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ErrorResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().code()).isEqualTo(ErrorCode.NOT_FOUND.getCode());
    assertThat(response.getBody().message()).isEqualTo(ErrorType.NOT_FOUND_TOKEN.getMessage());

  }

}