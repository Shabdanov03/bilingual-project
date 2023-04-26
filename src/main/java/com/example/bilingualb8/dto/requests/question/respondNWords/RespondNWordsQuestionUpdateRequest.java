package com.example.bilingualb8.dto.requests.question.respondNWords;

import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record RespondNWordsQuestionUpdateRequest(
        String title,
        String statement,
        @Positive(message = "Duration can not be negative")
        Integer duration,
        @Positive(message = "Min words can not be negative")
        Integer minWords
) {
}
