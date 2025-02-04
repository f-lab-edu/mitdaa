package com.flab.mitdaa.user.service;

import com.flab.mitdaa.exception.ErrorType;
import com.flab.mitdaa.exception.MitdaException;
import com.flab.mitdaa.user.dto.RegisterRequestDto;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.repository.UserRepository;
import com.flab.mitdaa.user.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private static final long EMAIL_TOKEN_EXPIRATION_MINUTES = 30;


    /*토큰 생성 메서드*/
    public VerificationToken createVerificationToken(User user) {
//        Objects.requireNonNull(user, "이메일은 반드시 존재해야합니다.");
        VerificationToken verificationToken = VerificationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryTime(LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_MINUTES))
                .build();

        return verificationTokenRepository.save(verificationToken);
    }

    @Transactional
    public void registerUser(RegisterRequestDto req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            throw new MitdaException(ErrorType.EMAIL_DUPLICATED);
        }
        /* 유저 저장 */
        User user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .email(req.email())
                .build();

         userRepository.save(user);

         /*토큰 생성 */
         VerificationToken verificationToken = createVerificationToken(user);

        // verificationService에 빼서 호출 transaction에 같이 있으면 안됨.
        String verificationUrl = "http://localhost:8088/api/verify?token=" + verificationToken.getToken();
        emailService.sendEmail(user.getEmail(), "이메일 인증을 완료해주세요.", "아래 링크를 클릭하여 이메일을 인증해주세요:\n" + verificationUrl);

    }

}
