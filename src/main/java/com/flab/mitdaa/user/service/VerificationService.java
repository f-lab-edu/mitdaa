package com.flab.mitdaa.user.service;

import com.flab.mitdaa.exception.ErrorType;
import com.flab.mitdaa.exception.MitdaException;
import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.repository.VerificationTokenRepository;
import com.flab.mitdaa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private static final long EMAIL_TOKEN_EXPIRATION_MINUTES = 10;

    /*신규 토큰 생성 */
    public String createVerificationToken(String email) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .email(email)
                .token(token)
                .expiryTime(LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_MINUTES))
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }


  /*메일 인증하기 서비스*/
    @Transactional
    public void verifyEmail(String token){

        /*토큰 유효청 체크 */
        VerificationToken verificationToken = verificationTokenRepository.findById(token)
                .orElseThrow(()-> new MitdaException(ErrorType.INVALID_TOKEN));

        // 토큰 기한 만료 체크
        if (verificationToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            verificationTokenRepository.deleteById(token); // 만료된 토큰 삭제
            throw new MitdaException(ErrorType.EXPIRED_TOKEN);
        }
        /*상태 업데이트 */
        User user = userRepository.findByEmail(verificationToken.getEmail())
                .orElseThrow(()-> new MitdaException(ErrorType.INVALID_EMAIL));

        if (user.isEmailVerified()) {
            throw new MitdaException(ErrorType.VERIFIED_EMAIL);
        }
        user.setEmailVerified(true);
        userRepository.save(user);
        verificationTokenRepository.deleteById(token); // 만료된 토큰 삭제


    }

    /*이메일 재전송 서비스*/
    @Transactional
    public void resendVerifyEmail(String email){
        //이메일이 DB에 이미 가입되어있는지 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MitdaException(ErrorType.NOT_EXIST_EMAIL));

        if(user.isEmailVerified()){
            throw new MitdaException(ErrorType.VERIFIED_EMAIL);
        }
        /* 이메일 재전송 (새 토큰) */
        createVerificationToken(email);

        String verificationUrl = "http://localhost:8088/api/verify?token=" + createVerificationToken(email);
        emailService.sendEmail(user.getEmail(), "이메일 인증을 완료해주세요.", "아래 링크를 클릭하여 이메일을 인증해주세요:\n" + verificationUrl);

    }
}
