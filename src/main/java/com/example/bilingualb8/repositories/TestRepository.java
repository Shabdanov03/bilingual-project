package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    boolean existsByTitle(String title);
}
