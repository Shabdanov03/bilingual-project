package com.example.bilingualb8.services.questions;

<<<<<<< HEAD
public interface RespondNWordsQuestionService {
=======
import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionRequest;
import com.example.bilingualb8.dto.requests.question.respondNWords.RespondNWordsQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.question.RespondNWordsQuestionResponse;

import java.util.List;

public interface RespondNWordsQuestionService {
    SimpleResponse respondNWordsSave(RespondNWordsQuestionRequest request);

    List<RespondNWordsQuestionResponse> getAllRespondNWords();

    RespondNWordsQuestionResponse findByIdRespondNWords(Long id);

    SimpleResponse deleteByIdRespondNWords(Long id);

    SimpleResponse updateRespondNWords(Long id, RespondNWordsQuestionUpdateRequest request);
>>>>>>> 347b759a835a654e3275416dc4d5558b652a66d8
}
