package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.MainQuestionMiniResponse;
import com.example.bilingualb8.dto.responses.questions.MainQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomMainQuestionRepository {
    List<MainQuestionMiniResponse> getAllQuestions();
    Optional<MainQuestionResponse> getQuestionById(Long id);
}
