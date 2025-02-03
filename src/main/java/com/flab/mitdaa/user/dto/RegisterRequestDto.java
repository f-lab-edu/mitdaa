package com.flab.mitdaa.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

// validation 체크 필요 인터페이스
public record RegisterRequestDto(
        String username ,
        String password,
        String email) implements Serializable , ValidCheck  {


    @Override
    public void check() {
        // Null 체크
        if (username == null) {
            throw new IllegalArgumentException("유저 이름이 비어 있습니다..");
        }
        if (password == null) {
            throw new IllegalArgumentException("패스워드 비어 있습니다.");
        }
        if (email == null) {
            throw new IllegalArgumentException("이메일이 비어 있습니다.");
        }
        // 유저 이름 길이 체크 (3 ~ 20자)
        if (username.length() < 3 || username.length() > 10) {
            throw new IllegalArgumentException("유저 이름은 3자에서 10자 사이로 입력하여야 합니다.");
        }
        // 패스워드 규격 체크 (최소 6자 이상)
        if (password.length() < 6) {
            throw new IllegalArgumentException("패스워드는 최소 6자 이상 이여야합니다.");
        }
        // 이메일 형식 체크
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}




