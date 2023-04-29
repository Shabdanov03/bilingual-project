package com.example.bilingualb8.dto.responses.questions.record_saying_statement;

import com.example.bilingualb8.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RecordSayingStatementQuestionResponse {
    private Long id;
    private String title;
    private String statement;
    private QuestionType questionType;
    private String correctAnswer;
    private Integer duration;
    private Integer minWords;
    private Integer questionOrder;
    private Long testId;

    public RecordSayingStatementQuestionResponse(Long id, QuestionType questionType, String title, String statement, Integer duration, Integer minWords, Integer questionOrder, Long testId) {
        this.id = id;
        this.questionType = questionType;
        this.title = title;
        this.statement = statement;
        this.duration = duration;
        this.minWords = minWords;
        this.questionOrder = questionOrder;
        this.testId = testId;
    }
}
