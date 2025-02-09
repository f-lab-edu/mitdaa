package com.flab.mitdaa.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "MUSERSTB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTime {

    @Id
    @Column(nullable = false ,unique = true , name = "USER_EMAIL")
    private String email;

    @Column(nullable = false , name = "USER_NM")  // user_name ,  소문자로 적는다.
    private String username;

    @Column(nullable = false , name = "PASSWORD")
    private String password;

    @Column(nullable = false , name="EMAIL_VERIFIED")
    private boolean emailVerified;

}
