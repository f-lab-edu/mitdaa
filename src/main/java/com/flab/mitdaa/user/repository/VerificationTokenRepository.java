package com.flab.mitdaa.user.repository;

import com.flab.mitdaa.user.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    //NPE 방지 (Optional)
    Optional<VerificationToken> findByToken(String token);

}
