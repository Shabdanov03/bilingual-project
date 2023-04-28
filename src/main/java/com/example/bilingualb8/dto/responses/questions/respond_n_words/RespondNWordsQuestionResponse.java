package com.example.bilingualb8.dto.responses.questions.respond_n_words;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record RespondNWordsQuestionResponse(
        Long id,
        String title,
        String statement,
        @Enumerated(EnumType.STRING)
        String questionType,
        Integer duration,
        Integer minWords,
        Long testId
) {
}
