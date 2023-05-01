package com.example.bilingualb8.dto.responses.questions;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MainQuestionMiniResponse {
    private Long id;
    private String title;
    private String statement;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.DESCRIBE_IMAGE;
    private Integer duration;
    private Integer minWords;
    private Integer numberOfReplays;
    private String correctAnswer;
    private String passage;
    private String audioText;
    private Long testId;
}
