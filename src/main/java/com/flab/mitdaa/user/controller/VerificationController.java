package com.flab.mitdaa.user.controller;



import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/verify")
    public  void verifyEmail(@RequestParam String token) {
        log.info("token!!!!!!!!!" + token);
        verificationService.verifyEmail(token);
    }


    @PostMapping("/resend")
    public void resendEmail(@RequestBody User req) {  // key 값을 알수가 없다, 스웨거에서도 확인 불가 . map 사용시 , 객체로 만들어서 수정 필요. void로 변경
        verificationService.resendVerifyEmail(req.getEmail());
    }
}
