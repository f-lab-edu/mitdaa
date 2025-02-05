package com.flab.mitdaa.user.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "VERIFICATION_TOKEN", timeToLive = 600) // TTL 10분 설정
public class VerificationToken implements Serializable {

    @Id
    private String token; // 인증 토큰
    private String email; // 메일
    private LocalDateTime expiryTime; // 만료 시간
}
