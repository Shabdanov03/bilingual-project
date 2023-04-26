package com.example.bilingualb8.dto.requests.question;

import com.example.bilingualb8.dto.requests.file.FileRequest;
import com.example.bilingualb8.entity.File;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TypeWhatYouHearQuestionRequest(
        @NotNull(message = "Title must not be null!")
        @NotBlank(message = "Title must not be empty!")
        String title,
        @NotNull(message = "Duration must not be null!")
        @NotBlank(message = "Duration must not be empty!")
        @Positive
        int duration,
        @NotNull(message = "Statement must not be null!")
        @NotBlank(message = "Statement must not be empty!")
        String statement,
        @NotNull(message = "Question Type must not be null!")
        @NotBlank(message = "Question Type must not be empty!")
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        @NotNull(message = "Number of replays must not be null!")
        @NotBlank(message = "Number of replays must not be empty!")
        @Positive
        int numberOfReplays,
        @NotNull(message = "File must not be null!")
        @NotBlank(message = "File must not be empty!")
        FileRequest file,
        @NotNull(message = "Correct answer must not be null!")
        @NotBlank(message = "Correct answer must not be empty!")
        String correctAnswer,
        @NotNull(message = "Test id must not be null!")
        @NotBlank(message = "Test id must not be empty!")
        @Positive
        Long testId
) {
}
