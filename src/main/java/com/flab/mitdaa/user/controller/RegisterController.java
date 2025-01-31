package com.flab.mitdaa.user.controller;


import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService ;

    @PostMapping// POST 매핑으로 수정
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto req) {
        registerService.registerUser(req);
        return ResponseEntity.ok("이메일을 확인 후 인증 해주세요.");
    }
}
