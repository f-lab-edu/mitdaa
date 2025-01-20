package com.flab.mitdaa.user.service;

import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User registerUser(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        User user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .email(req.email())
                .build();

        return userRepository.save(user);
    }

    //유저 검색
    public List<User> getUsers() {
        return userRepository.findAllBy();
    }


}
