package com.example.bilingualb8.services.questions;


import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.respond_n_words.RespondNWordsQuestionResponse;
import java.util.List;

public interface RespondNWordsQuestionService {
    SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request);

    List<RespondNWordsQuestionResponse> getAllRespondNWordsQuestion();

    RespondNWordsQuestionResponse getRespondNWordsQuestionById(Long id);

    SimpleResponse deleteRespondNWordsQuestionById(Long id);

    SimpleResponse updateRespondNWordsQuestionById(Long id, RespondNWordsQuestionUpdateRequest updateRequest);
}