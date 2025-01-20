package com.flab.mitdaa.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "MUSERSTB")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name = "USER_NM")
    private String username;

    @Column(nullable = false ,unique = true , name = "USER_EMAIL")
    private String email;

    @Column(nullable = false , name = "PASSWORD")
    private String password;

    @Column(nullable = false , name="EMAIL_VERIFIED")
    private String emailVerified = "N";

    //가입 시간 과 업데이트 시간 추가 필요
    @Column( name ="CREATE_DTIME")
    private String registerDtime;

    @Column( name="UPDATE_DTIME")
    private String updateDtime;

    @Builder
    public User(String username, String password , String email) {
        this.username =username;
        this.password = password;
        this.email = email;
        // 날짜 포맷으로 변환하여 저장
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.registerDtime = LocalDateTime.now().format(formatter);
        this.updateDtime = LocalDateTime.now().format(formatter);
    }

    public void verifyEmail() {
        this.emailVerified = "Y";
    }

}
