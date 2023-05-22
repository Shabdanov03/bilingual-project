package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.services.questions.RespondNWordsQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RespondNWordsQuestionServiceImpl implements RespondNWordsQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private static final Logger logger = LogManager.getLogger(Question.class);
    @Override
    public SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request) {
        logger.info("This is save " +request.getTitle() + " method");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .statement(request.getStatement())
                .questionType(QuestionType.RESPOND_N_WORDS)// delete
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .minWords(request.getMinWords())
                .test(test)
                .isActive(request.getIsActive())
                .build();
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateRespondNWordsQuestionById(Long id, RespondNWordsQuestionUpdateRequest updateRequest) {
        logger.info("This is update "+ updateRequest.getTitle() + "method");
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Question with id : %s doesn't exist ! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setStatement(updateRequest.getStatement() != null ? updateRequest.getStatement() : question.getStatement());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        question.setMinWords(updateRequest.getMinWords() != null ? updateRequest.getMinWords() : question.getMinWords());
        question.setIsActive(updateRequest.getIsActive() != null ? updateRequest.getIsActive() : question.getIsActive());

        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
