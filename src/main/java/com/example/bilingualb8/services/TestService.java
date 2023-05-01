package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import java.util.List;

public interface TestService {
    SimpleResponse save(TestRequest request);
    SimpleResponse deleteById(Long testId);
    TestResponse getById(Long testId);
    List<TestResponse> getAll();
    SimpleResponse update(Long id, TestRequest request);
}
