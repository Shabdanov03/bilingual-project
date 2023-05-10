package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;

import java.util.List;

public interface MyResultService {
    List<MyResultResponse> getAll(Long userId);
    SimpleResponse deleteById(Long id);
}
