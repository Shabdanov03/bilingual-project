package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.type_what_you_hear.TypeWhatYouHearQuestionResponse;

import java.util.List;
import java.util.Optional;

public interface CustomTypeWhatYouHearQuestionRepository {
    List<TypeWhatYouHearQuestionResponse> getAllTypeWhatYouHearQuestion();
    Optional<TypeWhatYouHearQuestionResponse> getTypeWhatYouHearQuestionById(Long id);
}
