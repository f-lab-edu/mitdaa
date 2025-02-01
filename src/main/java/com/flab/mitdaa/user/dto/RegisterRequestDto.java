package com.flab.mitdaa.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// validation 체크 필요 인터페이스
public record RegisterRequestDto(String username , String password, String email) implements Serializable , ValidCheck  {


    @Override
    public void check() {
        if(username == null){ // null체크 먼저  , 패스워드 규격 , 이메일 규격 , 유저이름 길이체크
            throw new IllegalArgumentException("username is null");
        }
    }
}




