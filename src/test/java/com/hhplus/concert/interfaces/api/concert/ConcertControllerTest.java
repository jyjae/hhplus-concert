package com.hhplus.concert.interfaces.api.concert;

import com.hhplus.concert.domain.concert.constants.ConcertDateSeatStatus;
import com.hhplus.concert.domain.token.dto.GetQueueTokenCommand;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateResponse;
import com.hhplus.concert.interfaces.api.concert.dto.GetConcertDateSeatResponse;
import com.hhplus.concert.util.UuidUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConcertControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private QueueTokenService queueTokenService;

    private final RestTemplate restTemplate = new RestTemplate();

    private String baseUrl(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 콘서트 날짜 조회 성공")
    @Test()
    void shouldGetConcertDatesSuccessfully() {
        Long concertId = 1L;
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GetConcertDateResponse[]> response = restTemplate.exchange(
                baseUrl("/concerts/" + concertId + "/concert-dates"),
                HttpMethod.GET,
                requestEntity,
                GetConcertDateResponse[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);

        GetConcertDateResponse firstDate = response.getBody()[0];
        assertThat(firstDate.getConcertId()).isEqualTo(concertId);
        assertThat(firstDate.getTotalCapacity()).isGreaterThanOrEqualTo(0);
        assertThat(firstDate.getCurrentCapacity()).isGreaterThanOrEqualTo(0);
        assertThat(firstDate.getStartDate()).isNotNull();
        assertThat(firstDate.getPlace()).isNotEmpty();
    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("유효하지 않은 토큰으로 예약 가능한 콘서트 날짜 조회 실패")
    @Test()
    void shouldGetConcertDatesFail() {
        Long concertId = 1L;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", UuidUtil.generateUuid());

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        assertThatThrownBy(() -> restTemplate.exchange(
                baseUrl("/concerts/" + concertId + "/concert-dates"),
                HttpMethod.GET,
                requestEntity,
                GetConcertDateResponse[].class
        )).isInstanceOf(HttpStatusCodeException.class)
                .hasMessageContaining("404")
                .hasMessageContaining("Token not found");

    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("예약 가능한 콘서트 날짜 좌석 조회 성공")
    @Test()
    void shouldGetConcertDateSeatsSuccessfully() {
        Long concertId = 1L;
        Long concertDateId = 1L;
        String token = queueTokenService.getQueueToken(new GetQueueTokenCommand(1L)).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<GetConcertDateSeatResponse[]> response = restTemplate.exchange(
                baseUrl("/concerts/" + concertId + "/concert-dates/"+concertDateId+"/seats"),
                HttpMethod.GET,
                requestEntity,
                GetConcertDateSeatResponse[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);

        GetConcertDateSeatResponse firstDate = response.getBody()[0];
        assertThat(firstDate.getConcertDateId()).isEqualTo(concertDateId);
        assertThat(firstDate.getPrice()).isGreaterThanOrEqualTo(0);
        assertThat(firstDate.getExpiredDate()).isNotNull();
        assertThat(firstDate.getStatus()).isEqualTo(ConcertDateSeatStatus.AVAILABLE);

    }

    @Sql({"/reset.sql", "/insert.sql"})
    @DisplayName("유효하지 않은 토큰으로 예약 가능한 콘서트 날짜 좌석 조회 성공")
    @Test()
    void shouldGetConcertDateSeatsFail() {
        Long concertId = 1L;
        Long concertDateId = 1L;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", UuidUtil.generateUuid());

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        assertThatThrownBy(() -> restTemplate.exchange(
                baseUrl("/concerts/" + concertId + "/concert-dates/"+concertDateId+"/seats"),
                HttpMethod.GET,
                requestEntity,
                GetConcertDateResponse[].class
        )).isInstanceOf(HttpStatusCodeException.class)
                .hasMessageContaining("404")
                .hasMessageContaining("Token not found");
    }


}