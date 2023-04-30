package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;
import com.example.bilingualb8.dto.responses.questions.record_saying_statement.RecordSayingStatementQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomHighlightTheAnswerQuestionRepository {
    List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion();
    Optional<HighlightTheAnswerQuestionResponse> getHighlightTheAnswerQuestionById(Long id);
}
