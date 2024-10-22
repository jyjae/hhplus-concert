package com.hhplus.concert.interfaces.api.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.interfaces.api.user.dto.GetQueueResponse;
import com.hhplus.concert.interfaces.api.user.dto.GetUserPointResponse;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

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
    String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(2L)).getToken();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("token", token);

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
    assertThat(response.getBody().getRank()).isEqualTo(0);
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("유효하지 않은 토큰으로 대기 순서 조회 실패")
  @Test
  void shouldFailGetQueueRankWithInvalidToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("token", UuidUtil.generateUuid());

    // When
    assertThatThrownBy(() -> restTemplate.exchange(
        baseUrl("/users/2/rank"),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        GetQueueResponse.class
    )).isInstanceOf(HttpStatusCodeException.class)
        .hasMessageContaining("404")
        .hasMessageContaining("Token not found");

  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("토큰 없이 대기 순서 조회 실패")
  @Test
  void shouldFailGetQueueRankWithNullToken() {
    // Given
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // When
    assertThatThrownBy(() -> restTemplate.exchange(
        baseUrl("/users/2/rank"),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        GetQueueResponse.class
    )).isInstanceOf(HttpStatusCodeException.class)
        .hasMessageContaining("404")
        .hasMessageContaining("token is null");

  }

}