package com.hhplus.concert.interfaces.api.error;

import com.hhplus.concert.exception.AlreadyExistsException;
import com.hhplus.concert.exception.BaseException;
import com.hhplus.concert.exception.InvalidException;
import com.hhplus.concert.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        logger.error(
            "[{}] 예외 발생 - URI: [{}], 메서드: [{}]",
            e.getClass().getSimpleName(),
            request.getRequestURI(),
            request.getMethod(),
            e
        );
        return ResponseEntity.status(500).body(new ErrorResponse(500, "에러가 발생했습니다."));
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e, HttpServletRequest request) {
        logger.info(
            "[{}] 예외 발생 - 에러 코드: [{}], URI: [{}], 메서드: [{}]",
            e.getClass().getSimpleName(),
            e.getErrorCode().getCode(),
            request.getRequestURI(),
            request.getMethod()
        );
        return ResponseEntity.status(200).body(new ErrorResponse(e.getErrorCode().getCode(), e.getMessage()));
    }
}
