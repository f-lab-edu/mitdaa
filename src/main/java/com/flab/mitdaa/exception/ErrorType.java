package com.flab.mitdaa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
 public enum ErrorType {

   // 유저 관련 , 인증관련 코드 나누기
    EMAIL_DUPLICATED(4001, "이미 사용중인 이메일 입니다.", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(4002, "유효하지 않은 이메일 형식입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(4003,"유효하지 않은 비밀번호 입니다 6자 이상으로 입력해주세요." , HttpStatus.BAD_REQUEST),
    EMPTY_PASSWORD(4004,"패드워드가 비어 있습니다.." ,HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(4005,"유저 이름은 3자에서 10자 사이로 입력하여야 합니다." ,HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4006,"유효하지 않은 토큰입니다." ,HttpStatus.BAD_REQUEST),
    EXPIRED_TOKEN(4007,"토큰 기한이 만료되었습니다." ,HttpStatus.BAD_REQUEST),
    VERIFIED_TOKEN(4008 ,"이미 인증이 완료된 토큰입니다." , HttpStatus.BAD_REQUEST),
    VERIFIED_EMAIL(4009, "해당 이메일은 이미 인증이 완료되었습니다." , HttpStatus.BAD_REQUEST),
    NOT_EXIST_EMAIL(4010 , "해당 이메일로 가입된 계정이 없습니다. 다시 확인해주세요." ,HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

}
