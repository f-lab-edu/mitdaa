package com.flab.mitdaa.user.repository;

import com.flab.mitdaa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    //findAll 사용.
    List<User> findAllBy();
    // 이메일 조회
    Optional<User> findByEmail(String email);

}
