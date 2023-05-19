package com.example.bilingualb8.dto.requests.answer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnswerRequest {
    @NotNull(message = "The question id must not be empty.")
    @Positive(message = "question id can not be negative")
    private Long questionId;
    private String data;
    @Positive(message = "Number of plays can not be negative")
    private Integer numberOfPlays;
    private String fileUrl;
    @Valid
    private List<Long> optionsIds;
}
