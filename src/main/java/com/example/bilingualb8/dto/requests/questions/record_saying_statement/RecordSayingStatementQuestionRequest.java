package com.example.bilingualb8.dto.requests.questions.record_saying_statement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordSayingStatementQuestionRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The statement must not be empty.")
    private String statement;
    @NotNull(message = "The correctAnswer must not be empty.")
    private String correctAnswer;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The Question Order must not be empty.")
    @Positive(message = "Question Order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The Test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The isActive id must not be empty.")
    private Boolean isActive;

    public RecordSayingStatementQuestionRequest() {
    }
    public RecordSayingStatementQuestionRequest(String title, String statement, String correctAnswer, Integer duration, Integer questionOrder, Long testId, Boolean isActive) {
        this.title = title;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.duration = duration;
        this.questionOrder = questionOrder;
        this.testId = testId;
        this.isActive = isActive;
    }
}

