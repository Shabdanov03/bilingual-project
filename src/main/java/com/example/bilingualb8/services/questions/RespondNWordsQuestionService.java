package com.example.bilingualb8.services.questions;


import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface RespondNWordsQuestionService {
    SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request);

    SimpleResponse updateRespondNWordsQuestionById(Long id, RespondNWordsQuestionUpdateRequest updateRequest);
}