package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.EvaluateQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.QuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomQuestionRepository {
    List<QuestionResponse> getAllQuestions();

    Optional<QuestionResponse> getQuestionById(Long id);
    EvaluateQuestionResponse getEvaluateQuestionByIdes(Long answerId, Long questionId);
}
