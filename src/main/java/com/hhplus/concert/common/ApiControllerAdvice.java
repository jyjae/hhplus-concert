package com.hhplus.concert.common;

import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.InvalidException;
import com.hhplus.concert.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
;

@RestControllerAdvice
class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error("에러가 발생했습니다: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(new ErrorResponse("500", "에러가 발생했습니다."));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        logger.error("에러가 발생했습니다: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(new ErrorResponse(e.getStatusCode(), e.getMessage()));
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        logger.error("에러가 발생했습니다: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(new ErrorResponse(e.getStatusCode(), e.getMessage()));
    }

    @ExceptionHandler(value = InvalidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException(InvalidException e) {
        logger.error("에러가 발생했습니다: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body(new ErrorResponse(e.getStatusCode(), e.getMessage()));
    }
}
