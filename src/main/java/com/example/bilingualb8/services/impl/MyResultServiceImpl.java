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
        log.info("This is get all my results method");
        return customResultRepository.getAll(userId);
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        log.info("This is delete my result by id method");
        resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("result with : %d id not found", id)));
        resultRepository.deleteById(id);
        return SimpleResponse.builder()
                .message(String.format("result with : %d id successfully deleted", id))
                .build();
    }
}
