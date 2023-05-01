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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondNWordsQuestionServiceImpl implements RespondNWordsQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request) {
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with id : %s doesn't exist !", request.getTestId())));
        Question question = Question.builder()
                .title(request.getTitle())
                .statement(request.getStatement())
                .questionType(QuestionType.RESPOND_N_WORDS)
                .duration(request.getDuration())
                .questionOrder(request.getQuestionOrder())
                .minWords(request.getMinWords())
                .test(test)
                .build();
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateRespondNWordsQuestionById(Long id, RespondNWordsQuestionUpdateRequest updateRequest) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Question with id : %s doesn't exist ! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setStatement(updateRequest.getStatement() != null ? updateRequest.getStatement() : question.getStatement());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        question.setMinWords(updateRequest.getMinWords() != null ? updateRequest.getMinWords() : question.getMinWords());
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
