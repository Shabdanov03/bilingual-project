package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface HighlightTheAnswerQuestionService {
    SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request);

    SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest);

}
