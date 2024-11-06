package com.hhplus.concert.interfaces.interceptor;

import com.hhplus.concert.domain.token.dto.FindQueueTokenQuery;
import com.hhplus.concert.domain.token.model.QueueToken;
import com.hhplus.concert.domain.token.service.QueueTokenService;
import com.hhplus.concert.exception.ErrorType;
import com.hhplus.concert.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class QueueTokenInterceptor implements HandlerInterceptor {

    private final QueueTokenService queueTokenService;

    public QueueTokenInterceptor(QueueTokenService queueTokenService) {
        this.queueTokenService = queueTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Access-Token");

        if (token == null) {
            return false;
        }

        if(!queueTokenService.findQueueToken(new FindQueueTokenQuery(token))) {
            throw new NotFoundException(ErrorType.NOT_FOUND_TOKEN);
        }

        return true;
    }
}
