package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.QuestionResponse;
import com.example.bilingualb8.entity.Answer;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Result;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.AnswerRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.custom.CustomQuestionRepository;
import com.example.bilingualb8.services.questions.MainQuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class MainQuestionServiceImpl implements MainQuestionService {
    private final CustomQuestionRepository customQuestionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<QuestionResponse> getAllQuestions() {
        log.info("Fetching all questions");
        return customQuestionRepository.getAllQuestions();
    }

    @Override
    public QuestionResponse getQuestionById(Long id) {
        log.info("Fetching question by ID: {}", id);
        return customQuestionRepository.getQuestionById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s doesn't exist", id)));
    }

    @Transactional
    @Override
    public SimpleResponse deleteQuestionById(Long id) {
        log.info("Deleting question with ID: {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s doesn't exist", id)));

        Answer answer = answerRepository.findAnswerByQuestionId(question.getId());
        if (answer != null) {
            log.info("Deleting answer associated with question ID: {}", question.getId());
            answerRepository.delete(answer);
        }

        List<Result> results = resultRepository.findByTestId(question.getTest().getId());
        for (Result result : results) {
            result.getAnswers().remove(answer);
        }

        questionRepository.delete(question);

        log.info("Question with ID {} deleted successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s successfully deleted", id))
                .build();
    }
}
