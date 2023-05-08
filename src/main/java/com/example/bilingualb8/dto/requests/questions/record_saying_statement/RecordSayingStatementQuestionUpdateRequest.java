package com.example.bilingualb8.dto.requests.questions.record_saying_statement;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordSayingStatementQuestionUpdateRequest {
    private String title;
    private String statement;
    private String correctAnswer;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "MinWords can not be negative")
    private Integer minWords;
    @Positive(message = "Question Order can not be negative")
    private Integer questionOrder;
    private Boolean isActive;
}
