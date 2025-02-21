package com.flab.mitdaa.user.dto;


import com.flab.mitdaa.exception.ErrorType;
import com.flab.mitdaa.exception.MitdaaException;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Map;

// validation 체크 필요 인터페이스
public record RegisterRequestDto(
        @NonNull
        String username ,
        @NonNull
        String password,
        @NonNull
        String email) implements Serializable , ValidCheck  {


    @Override
    public void check() {
        // 유저 이름 길이 체크 (3 ~ 20자)
        if (username.length() < 3 || username.length() > 10) {
            throw new MitdaaException(ErrorType.INVALID_USERNAME
                                   , Map.of("UserName" , username)
                                   , (logMessage) -> System.out.println("logMessage: " + logMessage));
        }
        // 패스워드 규격 체크 (최소 6자 이상)
        if (password.length() < 6) {
            throw new MitdaaException(ErrorType.INVALID_PASSWORD
                                   , Map.of("password" , password)
                                   , (logMessage) -> System.out.println("logMessage: " + logMessage));
        }
        // 이메일 형식 체크
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new MitdaaException(ErrorType.INVALID_EMAIL
                                   , Map.of("email" , email)
                                   , (logMessage) -> System.out.println("logMessage: " + logMessage));
        }
    }
}




