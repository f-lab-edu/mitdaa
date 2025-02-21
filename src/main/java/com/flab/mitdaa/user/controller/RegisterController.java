package com.flab.mitdaa.user.controller;


import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService ;

    @PostMapping
    public void register(@RequestBody RegisterRequestDto req) {
        req.check();
        registerService.registerUser(req);
    }
}
