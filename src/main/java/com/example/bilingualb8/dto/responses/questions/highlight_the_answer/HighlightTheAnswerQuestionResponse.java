package com.example.bilingualb8.dto.responses.questions.highlight_the_answer;

import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class HighlightTheAnswerQuestionResponse{
    private Long id;
    private String title;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.HIGHLIGHT_THE_ANSWER;
    private String statement;
    private String passage;
    private String correctAnswer;
    private Integer duration;
    private Integer questionOrder;
    private Long testId;
}
