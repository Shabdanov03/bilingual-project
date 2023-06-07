package com.example.bilingualb8.dto.requests.questions.record_saying_statement;

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
    @Positive(message = "Question Order can not be negative")
    private Integer questionOrder;
    private Boolean isActive;
    public RecordSayingStatementQuestionUpdateRequest(String title, String statement, String correctAnswer,
                                                      Integer duration, Integer questionOrder, Boolean isActive) {
        this.title = title;
        this.statement = statement;
        this.correctAnswer = correctAnswer;
        this.duration = duration;
        this.questionOrder = questionOrder;
        this.isActive = isActive;
    }

    public RecordSayingStatementQuestionUpdateRequest() {
    }
}
