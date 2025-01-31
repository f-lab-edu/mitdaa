package com.flab.mitdaa.user.repository;

import com.flab.mitdaa.user.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    //NPE 방지 (Optional)
    Optional<VerificationToken> findByToken(String token);
}
