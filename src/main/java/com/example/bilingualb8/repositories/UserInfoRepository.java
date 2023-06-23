package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByResetPasswordToken(String token);
    Optional<UserInfo> findByUserId(Long userId);
}
