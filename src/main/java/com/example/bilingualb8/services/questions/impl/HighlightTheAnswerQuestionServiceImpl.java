package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.HighlightTheAnswerQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HighlightTheAnswerQuestionServiceImpl implements HighlightTheAnswerQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private static final Logger logger = LogManager.getLogger(Question.class);

    @Override
    public SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request) {
        log.info("This is save Highlight th answer question method");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .questionType(QuestionType.HIGHLIGHT_THE_ANSWER)
                .statement(request.getStatement())
                .passage(request.getPassage())
                .correctAnswer(request.getCorrectAnswer())
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .test(test)
                .isActive(request.getIsActive())
                .build();
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest) {
        log.info("This is update the Highlight the answer question");
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Question with id : %s doesn't exist ! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setStatement(updateRequest.getStatement() != null ? updateRequest.getStatement() : question.getStatement());
        question.setPassage(updateRequest.getPassage() != null ? updateRequest.getPassage() : question.getPassage());
        question.setCorrectAnswer(updateRequest.getCorrectAnswer() != null ? updateRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        question.setIsActive(updateRequest.getIsActive() != null ? updateRequest.getIsActive() : question.getIsActive());

        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
