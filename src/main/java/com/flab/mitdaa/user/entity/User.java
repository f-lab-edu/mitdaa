package com.flab.mitdaa.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MUSERSTB")
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
    private boolean emailVerified = false;


}
