package com.flab.mitdaa.user.service;

import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        userRepository.saveUser(req.getUsername(),req.getEmail(),req.getPassword());
    }

    //유저 검색
    public List<User> getUsers() {
        return userRepository.findUsers();
    }
}
