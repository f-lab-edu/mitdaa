package com.flab.mitdaa.user.repository;

import com.flab.mitdaa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM MUSERSTB", nativeQuery = true)
    List<User> findUsers();

    @Query(value = "SELECT * FROM MUSERSTB WHERE USER_EMAIL = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MUSERSTB (USER_NM, USER_EMAIL, PASSWORD) VALUES (:name, :email, :password)", nativeQuery = true)
    void saveUser(String name,  String email, String password);

}
