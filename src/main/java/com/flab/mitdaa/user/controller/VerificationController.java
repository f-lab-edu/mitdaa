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
        verificationService.verifyEmail(token );
    }


    @PostMapping("/resend")
    public void resendEmail(@RequestBody User req) {
        verificationService.resendVerifyEmail(req.getEmail());
    }
}
