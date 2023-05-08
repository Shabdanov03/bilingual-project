package com.example.bilingualb8.dto.requests.questions.respond_n_words;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespondNWordsQuestionUpdateRequest {
    private String title;
    private String statement;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    @Positive(message = "Min words can not be negative")
    private Integer minWords;
    private Boolean isActive;
}
