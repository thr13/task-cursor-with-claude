package com.task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handlerMemberException(MemberException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse body = new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getStatus().value());

        return ResponseEntity.status(e.getErrorCode().getStatus().value()).body(body);
    }
}