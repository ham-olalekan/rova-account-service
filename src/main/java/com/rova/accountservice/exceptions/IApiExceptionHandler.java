package com.rova.accountservice.exceptions;

import com.rova.accountservice.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class IApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommonsException.class)
    public ResponseEntity<Object> commonsException(CommonsException exception) {
        log.error("An expected exception was thrown :: ", exception);
        return ErrorResponseDto.build(exception.getStatus(), exception.getMessage());
    }
}
