package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestListResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.TestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    @Override
    public SimpleResponse save(TestRequest request) {
        if (testRepository.existsByTitle(request.title())){
            throw new AlreadyExistException(String.format("Test with : %s title already exists", request.title()));
        }
        Test test = new Test();
        test.setTitle(request.title());
        test.setShortDescription(request.shortDescription());
        testRepository.save(test);
        return SimpleResponse.builder()
                .message(String.format("Test with : %s title successfully saved", request.title()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long testId) {
        testRepository.findById(testId).orElseThrow(()-> new NotFoundException(String.format("Test with : %d id not found", testId)));
        testRepository.deleteById(testId);
        return SimpleResponse.builder()
                .message(String.format("Test with : %d id successfully deleted", testId))
                .build();
    }

    @Override
    public TestListResponse getById(Long testId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new NotFoundException(String.format("Test with : %d id not found", testId)));
        int duration = 0;
        for (Question question : test.getQuestions()) {
            duration += question.getDuration();
        }
        return TestListResponse.builder()
                .title(test.getTitle())
                .shortDescription(test.getShortDescription())
                .duration(duration)
                .build();
    }

    @Override
    public List<TestListResponse> getAll() {
        List<Test> tests = testRepository.findAll();
        List<TestListResponse> testResponses = new ArrayList<>();
        int duration = 0;
        for (Test test : tests) {
            for (Question question : test.getQuestions()) {
                duration += question.getDuration();
            }
            testResponses.add(TestListResponse.builder()
                    .title(test.getTitle())
                    .shortDescription(test.getShortDescription())
                    .duration(duration)
                    .build());
        }
        return testResponses;
    }

    @Override
    public SimpleResponse update(Long testId, TestRequest request) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new NotFoundException(String.format("Test with : %d id not found", testId)));
        test.setTitle(request.title());
        test.setShortDescription(request.shortDescription());
        return SimpleResponse.builder()
                .message(String.format("Test with : %d id successfully updated", testId))
                .build();
    }
}