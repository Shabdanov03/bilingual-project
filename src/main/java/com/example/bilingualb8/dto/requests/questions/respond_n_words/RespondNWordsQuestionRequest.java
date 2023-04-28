package com.example.bilingualb8.dto.requests.questions.respond_n_words;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record RespondNWordsQuestionRequest(
        @NotBlank(message = "The title must not be empty.")
        @NotNull(message = "The title must not be empty.")
        String title,
        @NotBlank(message = "The statement must not be empty.")
        @NotNull(message = "The statement must not be empty.")
        String statement,
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        @NotBlank(message = "The duration must not be empty.")
        @NotNull(message = "The duration must not be empty.")
        @Positive(message = "Duration can not be negative")
        Integer duration,
        @NotBlank(message = "The minWords must not be empty.")
        @NotNull(message = "The minWords must not be empty.")
        @Positive(message = "Min words can not be negative")
        Integer minWords,
        @NotBlank(message = "The test id must not be empty.")
        @NotNull(message = "The test id  must not be empty.")
        @Positive(message = "Test id can not be negative")
        Long testId
) {
}
