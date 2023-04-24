package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestListResponse;
import java.util.List;

public interface TestService {
    SimpleResponse save(TestRequest request);
    SimpleResponse deleteById(Long testId);
    TestListResponse getById(Long testId);
    List<TestListResponse> getAll();
    SimpleResponse update(Long id, TestRequest request);
}
