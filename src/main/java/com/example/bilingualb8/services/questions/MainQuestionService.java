package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.QuestionResponse;

import java.util.List;

public interface MainQuestionService {
    List<QuestionResponse> getAllQuestions();

    QuestionResponse getQuestionById(Long id);

    SimpleResponse deleteQuestionById(Long id);
}
