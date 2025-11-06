package com.task.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 회원 오류
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "M001", "이미 가입된 이메일입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M002", "회원 정보를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "M003", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus status; // HTTP 오류 코드
    private final String code; // 사용자 오류 코드
    private final String message; // 오류 메시지

    private ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}