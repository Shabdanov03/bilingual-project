package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.test.TestResponse;

import java.util.List;
import java.util.Optional;

public interface CustomTestRepository {
    List<TestResponse> getAll();

    Optional<TestResponse> getById(Long id);
}
