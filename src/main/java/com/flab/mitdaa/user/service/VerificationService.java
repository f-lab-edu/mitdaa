package com.flab.mitdaa.user.service;

import com.flab.mitdaa.user.entity.User;
import com.flab.mitdaa.user.entity.VerificationToken;
import com.flab.mitdaa.user.repository.UserRepository;
import com.flab.mitdaa.user.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;


    /*메일 인증하기 서비스*/
    @Transactional
    public void verifyEmail(String token){
        /*토큰 유효청 체크 */
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
//              .filter(vt -> !"Y".equals(vt.getEmailVerified()))  분기처리 한번에  불가능?
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        /*토큰 인증 여부 체크*/
        if("Y".equals(verificationToken.getEmailVerified())){
               throw new IllegalArgumentException("이미 인증이 완료된 토큰입니다.");
        }

        /*토큰 기한 만료 체크 */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime expiryDateTime = LocalDateTime.parse(verificationToken.getExpiryTime(), formatter);
        if (expiryDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("토큰 기한이 만료되었습니다.");
        }

        /*상태 업데이트 */
        verificationToken.setUpdateDateTime(LocalDateTime.now().format(formatter));
        verificationToken.setEmailVerified("Y");
        verificationToken.getUser().setEmailVerified("Y");
        verificationToken.getUser().setUpdateDtime(LocalDateTime.now().format(formatter));
    }


    /*이메일 재전송 서비스*/
    @Transactional
    public void resendVerifyEmail(String email){

        System.out.println("Email: " + email);
        //이메일이 DB에 이미 가입되어있는지 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 가입된 계정이 없습니다. 다시 확인해주세요."));

        if("Y".equals(user.getEmailVerified())){
            throw new IllegalArgumentException("해당 이메일은 이미 인증이 완료되었습니다.");
        }
        /* 이메일 재전송 (새 토큰) */
        VerificationToken verificationToken = VerificationToken.builder()
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);

        String verificationUrl = "http://localhost:8088/api/verify?token=" + verificationToken.getToken();
        emailService.sendEmail(user.getEmail(), "이메일 인증을 완료해주세요.", "아래 링크를 클릭하여 이메일을 인증해주세요:\n" + verificationUrl);

    }
}
