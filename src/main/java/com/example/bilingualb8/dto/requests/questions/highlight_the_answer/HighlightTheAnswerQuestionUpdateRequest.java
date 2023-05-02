package com.example.bilingualb8.dto.requests.questions.highlight_the_answer;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HighlightTheAnswerQuestionUpdateRequest {
    private String title;
    private String statement;
    private String passage;
    private String correctAnswer;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
}
