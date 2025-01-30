package com.flab.mitdaa.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MTOKENTB")
public class VerificationToken {

    private static final long EMAIL_TOKEN_EXPIRATION_MINUTES = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name = "TOKEN")
    private String token;

    @OneToOne
    @JoinColumn(name = "USER_EMAIL")
    private User userEmail;

    @Column(nullable = false , name = "EXPIRY_DTIME")
    private String expiryTime;

    @Column(nullable = false , name="EMAIL_VERIFIED")
    private String emailVerified = "N";

    @CreatedDate
    @Column(nullable = false , name = "CREATE_DTIME" ,updatable = false)
    private String createDateTime;

    @LastModifiedDate
    @Column(nullable = false , name ="UPDATE_DTIME")
    private String updateDateTime;

    @Builder
    private VerificationToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.userEmail = Objects.requireNonNull(user, "이메일은 반드시 존재해야합니다.");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.expiryTime = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_MINUTES).format(formatter);
        this.createDateTime = LocalDateTime.now().format(formatter);
        this.updateDateTime = LocalDateTime.now().format(formatter);

    }
}
