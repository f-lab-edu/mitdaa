package com.flab.mitdaa.user.service;

import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository; // FINAL 추가

    @Transactional
    public User registerUser(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
        User user = new User(req.username(), req.password(), req.email());
        return userRepository.save(user);
    }
    //유저 검색
    public List<User> getUsers() {
        return userRepository.findAllBy();
    }


}
