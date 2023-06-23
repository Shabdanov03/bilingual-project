package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.requests.test.PassTestRequest;
import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import com.example.bilingualb8.entity.*;
import com.example.bilingualb8.enums.AnswerStatus;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.enums.ResultStatus;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.*;
import com.example.bilingualb8.repositories.custom.CustomTestRepository;
import com.example.bilingualb8.services.AnswerService;
import com.example.bilingualb8.services.ResultService;
import com.example.bilingualb8.services.TestService;
import com.example.bilingualb8.services.questions.MainQuestionService;
import com.example.bilingualb8.utils.QuestionScoreGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final CustomTestRepository customTestRepository;
    private final QuestionRepository questionRepository;
    private final MainQuestionService mainQuestionService;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;
    private final ResultService resultService;
    private final QuestionScoreGenerator questionScoreGenerator;


    @Override
    public SimpleResponse save(TestRequest request) {
        log.info("Saving test: {}", request.title());
        if (testRepository.existsByTitle(request.title())) {
            String errorMessage = String.format("Test with title '%s' already exists", request.title());
            log.error(errorMessage);
            throw new AlreadyExistException(errorMessage);
        }

        Test test = Test.builder()
                .title(request.title())
                .shortDescription(request.shortDescription())
                .isActive(request.isActive())
                .build();
        testRepository.save(test);
        log.info("Test saved successfully: {}", request.title());
        return SimpleResponse.builder()
                .message(String.format("Test with title '%s' successfully saved", request.title())).build();
    }

    @Override
    public SimpleResponse deleteById(Long testId) {
        log.info("Deleting test with id: {}", testId);
        testRepository.findById(testId)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Test with id '%d' not found", testId);
                    log.error(errorMessage);
                    return new NotFoundException(errorMessage);
                });

        List<Question> questions = questionRepository.findQuestionsByTestId(testId);
        List<Result> results = resultRepository.findByTestId(testId);

        if (!questions.isEmpty()) {
            for (Question question : questions) {
                mainQuestionService.deleteQuestionById(question.getId());
            }
        }

        if (!results.isEmpty()) {
            for (Result result : results) {
                resultRepository.deleteById(result.getId());
            }
        }

        testRepository.deleteById(testId);

        log.info("Test with id '{}' successfully deleted", testId);

        return SimpleResponse.builder()
                .message(String.format("Test with id '%d' successfully deleted", testId))
                .build();
    }

    @Override
    public TestResponse getById(Long testId) {
        log.info("Getting test by id: {}", testId);

        return customTestRepository.getById(testId).orElseThrow(
                () -> {
                    String errorMessage = String.format("Test with id '%s' was not found", testId);
                    log.error(errorMessage);
                    return new NotFoundException(errorMessage);
                }
        );
    }

    @Override
    public List<TestResponse> getAll() {
        log.info("Getting all tests");
        return customTestRepository.getAll();
    }

    @Override
    public SimpleResponse update(Long testId, TestRequest request) {
        log.info("Updating test with id: {}", testId);

        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new NotFoundException(String.format("Test with id '%d' not found", testId)));

        test.setTitle(request.title());
        test.setShortDescription(request.shortDescription());
        test.setIsActive(request.isActive());
        testRepository.save(test);

        log.info("Test with id '{}' successfully updated", testId);

        return SimpleResponse.builder()
                .message(String.format("Test with id '%d' successfully updated", testId))
                .build();
    }

    @Override
    public SimpleResponse submitTest(PassTestRequest request, Authentication authentication) {

        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NotFoundException(String.format("Test with id %s was not found", request.getTestId())));

        UserInfo userInfo = (UserInfo) authentication.getPrincipal();

        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s was not found", userInfo.getId())));

        List<Answer> answers = answerService.createAnswers(request.getAnswers(), user);

        answerRepository.saveAll(answers);

        Result result = resultService.createResult(test, user, answers);

        for (Answer answer : answers) {
            if (answer.getQuestion().getQuestionType() == QuestionType.LISTEN_AND_SELECT_ENGLISH_WORD
                    || answer.getQuestion().getQuestionType() == QuestionType.SELECT_ENGLISH_WORD) {
                float v = questionScoreGenerator.scoreGenerator(answer);
                answer.setAnswerStatus(AnswerStatus.EVALUATED);
                answer.setEvaluatedScore(v);
                result.setScore(result.getScore() + v);
            }
        }

        boolean allTrue = result.getAnswers().stream()
                .allMatch(a -> a.getAnswerStatus() == AnswerStatus.EVALUATED);

        if (allTrue){
            result.setStatus(ResultStatus.EVALUATED);
        }else result.setStatus(ResultStatus.NOT_EVALUATED);

        resultRepository.save(result);

        return SimpleResponse.builder()
                .message("Test submitted successfully!")
                .build();
    }
}