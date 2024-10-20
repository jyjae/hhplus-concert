package com.hhplus.concert.application.token;

import com.hhplus.concert.common.TimeProvider;
import com.hhplus.concert.domain.token.QueueToken;
import com.hhplus.concert.domain.token.QueueTokenRepository;
import com.hhplus.concert.domain.token.QueueTokenStatus;
import com.hhplus.concert.util.UuidUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @InjectMocks
    private QueueTokenService tokenService;

    @Mock
    private QueueTokenRepository queueTokenRepository;

    @Mock
    private TimeProvider timeProvider;

    @Test
    @DisplayName("대기열 발급 성공")
    void shouldIssueQueueTokenSuccessfully() {
        // given
        String uuid = UuidUtil.generateUuid();
        CreateQueueTokenCommand command = new CreateQueueTokenCommand(1L, uuid);
        when(timeProvider.getCurrentInstantPlusMinutes(30)).thenReturn(20220101L);

        // when
        when(queueTokenRepository.token(1L, uuid, timeProvider.getCurrentInstantPlusMinutes(30))).thenReturn(uuid);
        String token = tokenService.createQueueToken(command);

        // then
        assertThat(token).isNotNull();
        assertThat(token).isEqualTo(uuid);
    }

    @Test
    @DisplayName("유저 대기 순서 조회 성공 테스트")
    void shouldRetrieveUserQueueOrderSuccessfully() {
        // given
        GetUserQueueRankQuery command = new GetUserQueueRankQuery(150L, 100);
        when(queueTokenRepository.lastProcessingToken(QueueTokenStatus.PROCESSING)).thenReturn(100L);

        // when
        Long rank = tokenService.userRank(command);

        // then
        assertThat(rank).isNotNull();
        assertThat(rank).isEqualTo(0L);

    }

    @Test
    @DisplayName("토큰 검증 실패")
    void findToken() {
        // given
        FindQueueTokenQuery command = new FindQueueTokenQuery("token");
        when(timeProvider.getCurrentTimestamp()).thenReturn(20220101L);
        when(queueTokenRepository.findToken("token", timeProvider.getCurrentTimestamp())).thenReturn(null);

        // when
        QueueToken token = tokenService.findQueueToken(command);

        // then
        assertThat(token).isNull();
    }



}