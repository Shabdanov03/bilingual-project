package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.custom.CustomResultRepository;
import com.example.bilingualb8.services.MyResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyResultServiceImpl implements MyResultService {
    private final CustomResultRepository customResultRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<MyResultResponse> getAll(Long userId) {
        log.info("Fetching all results for user with ID: {}", userId);
        List<MyResultResponse> results = customResultRepository.getAll(userId);
        log.info("Retrieved {} results for user with ID: {}", results.size(), userId);
        return results;
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        log.info("Deleting result with ID: {}", id);
        resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Result with ID %d not found", id)));
        resultRepository.deleteById(id);
        log.info("Result with ID {} deleted successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Result with ID %d successfully deleted", id))
                .build();
    }
}
