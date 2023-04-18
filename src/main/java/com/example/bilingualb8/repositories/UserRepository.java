package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.User;
import com.example.bilingualb8.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(" select u from UserInfo u where u.email = ?1")
    Optional<UserInfo> findUserInfoByEmail(String email);

    boolean existsByUserInfoEmail(String email);

}
