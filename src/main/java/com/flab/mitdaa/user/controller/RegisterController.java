package com.flab.mitdaa.user.controller;


import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService ;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto req) {
        System.out.println(req.toString());
        registerService.registerUser(req);
//        List<User> test = registerService.getUsers(); //DB 연동확인
        return ResponseEntity.ok("이메일을 확인 후 인증 해주세요.");
       
    }
}
