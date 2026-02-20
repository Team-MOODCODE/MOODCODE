package com.devcrew.moodcode.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 1. Common (시스템 전반)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "서버 내부 오류입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C002", "잘못된 입력입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C003", "입력 타입이 유효하지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C004", "허용되지 않은 HTTP 메서드입니다."),

    // 2. Auth (인증/인가)
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "A001", "로그인이 필요합니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "만료된 토큰입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "A004", "접근 권한이 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "A005", "비밀번호가 일치하지 않습니다."),

    // 3. User (회원)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "회원을 찾을 수 없습니다."),
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "U002", "이미 존재하는 이메일입니다."),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "U003", "아이디 또는 비밀번호가 잘못되었습니다."),
    NICKNAME_DUPLICATE(HttpStatus.NOT_FOUND, "U004", "닉네임을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;    // 프론트 식별 코드
    private final String message; // 사용자에게 보여줄 메시지
}