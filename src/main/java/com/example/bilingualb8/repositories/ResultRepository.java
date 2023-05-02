package com.example.bilingualb8.repositories;

import com.example.bilingualb8.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByTestId(Long id);
}
