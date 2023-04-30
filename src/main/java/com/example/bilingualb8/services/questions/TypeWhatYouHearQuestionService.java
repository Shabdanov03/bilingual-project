package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.type_what_you_hear.TypeWhatYouHearQuestionResponse;

import java.lang.reflect.Type;
import java.util.List;

public interface TypeWhatYouHearQuestionService {

    SimpleResponse saveTypeWhatYouHearQuestion(TypeWhatYouHearQuestionRequest request);
    List<TypeWhatYouHearQuestionResponse> getAllTypeWhatYouHearQuestion();
    TypeWhatYouHearQuestionResponse getTypeWhatYouHearQuestionById(Long id);
    SimpleResponse deleteTypeWhatYouHearById(Long id);
    SimpleResponse updateTypeWhatYouHear(Long id, TypeWhatYouHearQuestionUpdateRequest updateQuestionRequest);
}
