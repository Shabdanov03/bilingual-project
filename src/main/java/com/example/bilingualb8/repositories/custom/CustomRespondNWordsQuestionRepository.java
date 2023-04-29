package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomRespondNWordsQuestionRepository {
    List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion();
    Optional<RespondNWordsQuestionResponse> getRespondNWordsQuestionById(Long id);
}
