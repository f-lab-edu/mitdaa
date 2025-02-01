package com.flab.mitdaa.user.entity;

import jakarta.persistence.*;
import lombok.*;
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
@AllArgsConstructor
@Builder
@Table(name = "MTOKENTB")
public class VerificationToken extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name = "TOKEN")
    private String token;

    @OneToOne // 수정 필요
    @JoinColumn(name = "USER_EMAIL")
    private User user;

    @Column(nullable = false , name = "EXPIRY_DTIME")
    private LocalDateTime expiryTime;

    @Column(nullable = false , name="EMAIL_VERIFIED")
    private String emailVerified;





}
