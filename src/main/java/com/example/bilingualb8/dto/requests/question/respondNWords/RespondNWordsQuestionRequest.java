package com.example.bilingualb8.dto.requests.question.respondNWords;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RespondNWordsQuestionRequest(
        @NotNull(message = "The title must not be empty.")
        String title,
        @NotNull(message = "The statement must not be empty.")
        String statement,
        @NotNull(message = "The duration must not be empty.")
        @Positive(message = "Duration can not be negative")
        Integer duration,
        @NotNull(message = "The questionType must not be empty.")
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        @NotNull(message = "The minWords must not be empty.")
        @Positive(message = "Min words can not be negative")
        Integer minWords,
        @NotNull(message = "The testId must not be empty.")
        @Positive(message = "Test id can not be negative")
        Long testId
) {
}
