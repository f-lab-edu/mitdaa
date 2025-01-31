package com.flab.mitdaa.user.controller;



import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/verify")
    public  ResponseEntity<String> verifyEmail(@RequestParam String token) {
        System.out.println("token!!!!!!!!!" + token);
        verificationService.verifyEmail(token);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }


    @PostMapping("/resend")
    public ResponseEntity<String> resendEmail(@RequestBody Map<String,String> req ) {

        verificationService.resendVerifyEmail(req.get("email"));
        return ResponseEntity.ok("인증 이메일이 재전송 되었습니다.");
    }
}
