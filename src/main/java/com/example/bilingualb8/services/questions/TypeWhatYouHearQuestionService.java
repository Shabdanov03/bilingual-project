package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface TypeWhatYouHearQuestionService {

    SimpleResponse saveTypeWhatYouHearQuestion(TypeWhatYouHearQuestionRequest request);

    SimpleResponse updateTypeWhatYouHear(Long id, TypeWhatYouHearQuestionUpdateRequest updateQuestionRequest);
}
