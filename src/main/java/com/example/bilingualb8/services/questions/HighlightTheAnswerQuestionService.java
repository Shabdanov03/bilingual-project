package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.highlight_the_answer.HighlightTheAnswerQuestionResponse;

import java.util.List;

public interface HighlightTheAnswerQuestionService {
    SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request);

    List<HighlightTheAnswerQuestionResponse> getAllHighlightTheAnswerQuestion();

    HighlightTheAnswerQuestionResponse getHighlightTheAnswerQuestionById(Long id);

    SimpleResponse deleteHighlightTheAnswerQuestionById(Long id);

    SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest);

}
