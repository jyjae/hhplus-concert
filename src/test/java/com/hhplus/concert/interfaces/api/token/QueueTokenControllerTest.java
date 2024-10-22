package com.hhplus.concert.interfaces.api.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenRequest;
import com.hhplus.concert.interfaces.api.token.dto.QueueTokenResponse;
import java.util.Collections;
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
class QueueTokenControllerTest {

  @LocalServerPort
  private int port;

  private RestTemplate restTemplate = new RestTemplate();


  private String baseUrl(String uri) {
    return "http://localhost:" + port + uri;
  }

  @Sql({"/reset.sql", "/insert.sql"})
  @DisplayName("대기열 토큰 생성 성공")
  @Test
  void shouldCreateQueueTokenSuccessfully() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    QueueTokenRequest request = new QueueTokenRequest(2L);
    HttpEntity<QueueTokenRequest> requestEntity = new HttpEntity<>(request, headers);

    ResponseEntity<QueueTokenResponse> response = restTemplate.exchange(
        baseUrl("/tokens"),
        HttpMethod.POST,
        requestEntity,
        QueueTokenResponse.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getToken()).isNotNull();
  }


}