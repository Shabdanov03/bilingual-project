package com.example.bilingualb8.dto.requests.questions.record_saying_statement;

import com.example.bilingualb8.dto.requests.file.FileRequest;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordSayingStatementQuestionRequest{
    @NotNull(message = "The Question Type must not be empty.")
    private QuestionType questionType;
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The statement must not be empty.")
    private String statement;
    @NotNull(message = "The correctAnswer must not be empty.")
    private String correctAnswer;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The minWords must not be empty.")
    @Positive(message = "MinWords can not be negative")
    private Integer minWords;
    @NotNull(message = "The Question Order must not be empty.")
    @Positive(message = "Question Order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The Test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
}

