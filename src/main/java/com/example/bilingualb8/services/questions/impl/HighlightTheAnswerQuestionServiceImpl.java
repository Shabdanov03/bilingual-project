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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HighlightTheAnswerQuestionServiceImpl implements HighlightTheAnswerQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request) {
        log.info("Saving highlight the answer question");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with ID %s does not exist!", request.getTestId())));

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

        log.info("Highlight the answer question saved successfully");
        return SimpleResponse.builder()
                .message(String.format("Question with title \"%s\" saved successfully!", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest) {
        log.info("Updating highlight the answer question with ID: {}", id);
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with ID %s does not exist!", id)));

        // Update question fields if provided in the update request
        if (updateRequest.getTitle() != null) {
            question.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getStatement() != null) {
            question.setStatement(updateRequest.getStatement());
        }
        if (updateRequest.getPassage() != null) {
            question.setPassage(updateRequest.getPassage());
        }
        if (updateRequest.getCorrectAnswer() != null) {
            question.setCorrectAnswer(updateRequest.getCorrectAnswer());
        }
        if (updateRequest.getDuration() != null) {
            question.setDuration(updateRequest.getDuration());
        }
        if (updateRequest.getQuestionOrder() != null) {
            question.setQuestionOrder(updateRequest.getQuestionOrder());
        }
        if (updateRequest.getIsActive() != null) {
            question.setIsActive(updateRequest.getIsActive());
        }

        questionRepository.save(question);

        log.info("Highlight the answer question with ID {} updated successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s updated successfully!", id))
                .build();
    }
}