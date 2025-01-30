package com.flab.mitdaa.user.service;

import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.repository.UserRepository;
import com.flab.mitdaa.user.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void registerUser(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        
        // 유저 저장
        User user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .email(req.email())
                .build();

         userRepository.save(user);
         
         // 이메일 전송
         VerificationToken verificationToken = VerificationToken.builder()
                 .user(user)
                 .build();
        verificationTokenRepository.save(verificationToken);

        String verificationUrl = "http://localhost:8088/api/verify?token=" + verificationToken.getToken();
//        emailService.sendEmail(user.getEmail(), "이메일 인증", "아래 링크를 클릭하여 이메일을 인증해주세요:\n" + verificationUrl);

    }
    //유저 검색
    public List<User> getUsers() {
        return userRepository.findAllBy();
    }


}
