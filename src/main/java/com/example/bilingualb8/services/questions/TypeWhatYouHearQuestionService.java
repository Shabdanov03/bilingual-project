package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.question.TypeWhatYouHearQuestionRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface TypeWhatYouHearQuestionService {
    SimpleResponse save(TypeWhatYouHearQuestionRequest request);
}
