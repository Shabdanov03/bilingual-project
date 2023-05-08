package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Result;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.repositories.custom.CustomTestRepository;
import com.example.bilingualb8.services.TestService;
import com.example.bilingualb8.services.questions.MainQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final CustomTestRepository customTestRepository;
    private final QuestionRepository questionRepository;
    private final MainQuestionService mainQuestionService;
    private final ResultRepository resultRepository;

    @Override
    public SimpleResponse save(TestRequest request) {
        if (testRepository.existsByTitle(request.title())) {
            throw new AlreadyExistException(String.format("Test with title : %s already exists", request.title()));
        }

        Test test = Test.builder()
                .title(request.title())
                .shortDescription(request.shortDescription())
                .build();
        testRepository.save(test);
        return SimpleResponse.builder()
                .message(String.format("Test with title : %s  successfully saved", request.title()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long testId) {
        testRepository.findById(testId).orElseThrow(() -> new NotFoundException(String.format("Test with : %d id not found", testId)));
        List<Question> questions = questionRepository.findQuestionsByTestId(testId);
        for (Question question : questions) {
            mainQuestionService.deleteQuestionById(question.getId());
        }
        for (Result result : resultRepository.findByTestId(testId)) {
            resultRepository.deleteById(result.getId());
        }
        testRepository.deleteById(testId);
        return SimpleResponse.builder()
                .message(String.format("Test with : %d id successfully deleted", testId))
                .build();
    }

    @Override
    public TestResponse getById(Long testId) {
        return customTestRepository.getById(testId).orElseThrow(
                () -> new NotFoundException(String.format("Test with id %s was not found", testId)));
    }

    @Override
    public List<TestResponse> getAll() {
        return customTestRepository.getAll();
    }

    @Override
    public SimpleResponse update(Long testId, TestRequest request) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new NotFoundException(String.format("Test with : %d id not found", testId)));
        test.setTitle(request.title());
        test.setShortDescription(request.shortDescription());
        testRepository.save(test);
        return SimpleResponse.builder()
                .message(String.format("Test with : %d id successfully updated", testId))
                .build();
    }
