package com.example.bilingualb8.dto.requests.questions.respond_n_words;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespondNWordsQuestionRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The statement must not be empty.")
    private String statement;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "Order can not be negative")
    @NotNull(message = "The question order must not be empty.")
    private Integer questionOrder;
    @NotNull(message = "The minWords must not be empty.")
    @Positive(message = "Min words can not be negative")
    private Integer minWords;
    @NotNull(message = "The test id  must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
}
