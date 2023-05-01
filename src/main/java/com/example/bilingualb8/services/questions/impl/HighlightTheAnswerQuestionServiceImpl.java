package com.example.bilingualb8.services.questions.impl;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;
import com.example.bilingualb8.entity.Answer;
import com.example.bilingualb8.entity.Question;
import com.example.bilingualb8.entity.Result;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.enums.QuestionType;
import com.example.bilingualb8.exceptions.NotFoundException;
import com.example.bilingualb8.repositories.AnswerRepository;
import com.example.bilingualb8.repositories.QuestionRepository;
import com.example.bilingualb8.repositories.ResultRepository;
import com.example.bilingualb8.repositories.TestRepository;
import com.example.bilingualb8.repositories.custom.CustomHighlightTheAnswerQuestionRepository;
import com.example.bilingualb8.services.questions.HighlightTheAnswerQuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HighlightTheAnswerQuestionServiceImpl implements HighlightTheAnswerQuestionService {
    private final CustomHighlightTheAnswerQuestionRepository customRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final AnswerRepository answerRepository;
    private final ResultRepository resultRepository;
    @Override
    public SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request) {
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
                .build();
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with title : %s successfully saved !", request.getTitle()))
                .build();
    }

    @Override
    public List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion() {
        return customRepository.getAllHighlightTheAnswerQuestion();
    }

    @Override
    public HighlightTheAnswerQuestionResponse getHighlightTheAnswerQuestionById(Long id) {
        return customRepository.getHighlightTheAnswerQuestionById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Question with id : %s doesn't exist !",id)));
    }

    @Transactional
    @Override
    public SimpleResponse deleteHighlightTheAnswerQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with id : %s doesn't exist !", id)));
        Answer answer = answerRepository.findAnswerByQuestionId(question.getId());
        List<Result> results = resultRepository.findByTestId(question.getTest().getId());
        for (Result result : results) {
            result.getAnswers().remove(answer);
        }
        answerRepository.delete(answer);
        questionRepository.delete(question);
     return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully deleted !",id))
                .build();
    }

    @Override
    public SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Question with id : %s doesn't exist ! ", id)));

        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setStatement(updateRequest.getStatement() != null ? updateRequest.getStatement() : question.getStatement());
        question.setPassage(updateRequest.getPassage() != null ? updateRequest.getPassage() : question.getPassage());
        question.setCorrectAnswer(updateRequest.getCorrectAnswer() != null ? updateRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        questionRepository.save(question);
        return SimpleResponse.builder()
                .message(String.format("Question with id : %s successfully updated !", id))
                .build();
    }
}
