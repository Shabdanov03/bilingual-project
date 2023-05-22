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
        log.info("This is get all method");
        return customQuestionRepository.getAllQuestions();
    }

    @Override
    public QuestionResponse getQuestionById(Long id) {
        log.info("This is get by id method");
        return customQuestionRepository.getQuestionById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with id : %s doesn't exist !", id)));
    }

    @Transactional
    @Override
    public SimpleResponse deleteQuestionById(Long id) {
        log.info("This is delete method");
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist !", id)));
        Answer answer = answerRepository.findAnswerByQuestionId(question.getId());
        List<Result> results = resultRepository.findByTestId(question.getTest().getId());
        for (Result result : results) {
            result.getAnswers().remove(answer);
        }
        if(answer != null) answerRepository.delete(answer);
        questionRepository.delete(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully deleted !", id))
                .build();
    }
}
