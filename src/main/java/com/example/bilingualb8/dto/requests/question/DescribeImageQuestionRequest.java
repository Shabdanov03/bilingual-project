package com.example.bilingualb8.dto.requests.question;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record DescribeImageQuestionRequest(
        String title,
        String correctAnswer,
        int duration,
        @Enumerated(EnumType.STRING)
        QuestionType questionType,
        Long testId
        ) {
}
