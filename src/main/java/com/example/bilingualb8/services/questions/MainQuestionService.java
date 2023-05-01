package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.responses.questions.MainQuestionMiniResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionResponse;

import java.util.List;

public interface MainQuestionService {
    List<MainQuestionMiniResponse> getAllQuestions();
    MainQuestionResponse getQuestionById(Long id);
}
