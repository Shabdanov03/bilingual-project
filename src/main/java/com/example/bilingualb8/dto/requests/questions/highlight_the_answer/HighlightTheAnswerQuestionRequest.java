package com.example.bilingualb8.dto.requests.questions.highlight_the_answer;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HighlightTheAnswerQuestionRequest {
    @NotNull(message = "The title must not be empty !")
    private String title;
    @Builder.Default
    @NotNull(message = "The question type must not be empty.")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.HIGHLIGHT_THE_ANSWER;
    @NotNull(message = "The statement must not be empty.")
    private String statement;
    @NotNull(message = "The passage must not be empty.")
    private String passage;
    @NotNull(message = "The correct answer must not be empty.")
    private String correctAnswer;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;

}
