package com.example.bilingualb8.services.impl;

import com.example.bilingualb8.dto.requests.answer.AnswerRequest;
import com.example.bilingualb8.dto.requests.test.PassTestRequest;
import com.example.bilingualb8.dto.requests.test.TestRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.test.TestResponse;
import com.example.bilingualb8.entity.*;
import com.example.bilingualb8.enums.AnswerStatus;
import com.example.bilingualb8.enums.FileType;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.AlreadyExistException;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.*;
import com.example.bilingualb8.repositories.custom.CustomTestRepository;
import com.example.bilingualb8.services.TestService;
import com.example.bilingualb8.services.questions.MainQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final CustomTestRepository customTestRepository;
    private final QuestionRepository questionRepository;
    private final MainQuestionService mainQuestionService;
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final OptionRepository optionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public SimpleResponse save(TestRequest request) {
        if (testRepository.existsByTitle(request.title())) {
            throw new AlreadyExistException(String.format("Test with title : %s already exists", request.title()));
        }

        Test test = Test.builder()
                .title(request.title())
                .shortDescription(request.shortDescription())
                .isActive(request.isActive())
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
        List<Result> results = resultRepository.findByTestId(testId);

        if (!questions.isEmpty()) for (Question question : questions) {
            mainQuestionService.deleteQuestionById(question.getId());
        }

        if (!results.isEmpty()) for (Result result : resultRepository.findByTestId(testId)) {
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
        test.setIsActive(request.isActive());
        testRepository.save(test);
        return SimpleResponse.builder()
                .message(String.format("Test with : %d id successfully updated", testId))
                .build();
    }

    @Override
    public SimpleResponse submitTest(PassTestRequest request, Authentication authentication) {
        List<Answer> answers = new ArrayList<>();
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        User user = userRepository.findById(userInfo.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s was not found", userInfo.getId())));

        for (AnswerRequest answerRequest : request.getAnswers()) {
            Question question = questionRepository.findById(answerRequest.getQuestionId()).orElseThrow(
                    () -> new NotFoundException(String.format("Question with id %s was not found", answerRequest.getQuestionId()))
            );

            Answer answer = new Answer();
            answer.setUser(user);
            answer.setQuestion(question);
            answer.setAnswerStatus(AnswerStatus.NOT_EVALUATED);

            // setting up answer options
            if (answerRequest.getOptionsIds() != null) {
                List<Option> options = optionRepository.findAllById(answerRequest.getOptionsIds());
                answer.setOptions(options);
            }

            // setting up answer number of replays
            if (answerRequest.getNumberOfPlays() != null)
                answer.setNumberOfPlays(answerRequest.getNumberOfPlays());

            // setting up answer file
            if (answerRequest.getFileUrl() != null
                    && question.getQuestionType().equals(QuestionType.RECORD_SAYING_STATEMENT)) {
                File file = new File();
                file.setFileType(FileType.AUDIO);
                file.setFileUrl(answerRequest.getFileUrl());
                file.setQuestion(question);
                answer.setFiles(new ArrayList<>(List.of(file)));
            }

            // setting up answer data
            if (answerRequest.getData() != null) {
                if (question.getQuestionType() == QuestionType.DESCRIBE_IMAGE
                        || question.getQuestionType() == QuestionType.HIGHLIGHT_THE_ANSWER
                        || question.getQuestionType() == QuestionType.RESPOND_N_WORDS
                        || question.getQuestionType() == QuestionType.TYPE_WHAT_YOU_HEAR) {
                    answer.setData(answerRequest.getData());

                    // setting up number of words
                    answer.setNumberOfWords(answerRequest.getData().split(" ").length);
                }
            }
            answers.add(answer);
        }
        answerRepository.saveAll(answers);
        return SimpleResponse.builder()
                .message("Test submitted successfully!")
                .build();
    }
}