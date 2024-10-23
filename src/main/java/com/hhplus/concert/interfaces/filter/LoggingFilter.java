package com.hhplus.concert.interfaces.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    logRequest(requestWrapper);

    try {
      filterChain.doFilter(requestWrapper, responseWrapper);
    } finally {
      logResponse(responseWrapper);
      responseWrapper.copyBodyToResponse();
    }
  }
  private void logRequest(ContentCachingRequestWrapper request) {
    String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);

    log.info("[REQUEST] URI: {}, Method: {}, Headers: [{}], Body: {}",
        request.getRequestURI(),
        request.getMethod(),
        getHeaders(request),
        requestBody);
  }

  private void logResponse(ContentCachingResponseWrapper response) {
    String responseBody = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);

    log.info("[RESPONSE] Status: {}, Headers: [{}], Body: {}",
        response.getStatus(),
        getHeaders(response),
        responseBody);
  }

  private String getHeaders(HttpServletRequest request) {
    return Collections.list(request.getHeaderNames()).stream()
        .map(name -> name + ": " + request.getHeader(name))
        .collect(Collectors.joining(", "));
  }

  private String getHeaders(HttpServletResponse response) {
    return response.getHeaderNames().stream()
        .map(name -> name + ": " + response.getHeader(name))
        .collect(Collectors.joining(", "));
  }
}
