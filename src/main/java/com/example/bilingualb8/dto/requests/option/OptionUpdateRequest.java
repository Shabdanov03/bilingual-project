package com.example.bilingualb8.dto.requests.option;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionUpdateRequest {
    @NotNull(message = "The Option id must not be empty.")
    private Long id;
    @NotNull(message = "Title can not be null")
    private String title;
    @NotNull(message ="Is Correct can not be null")
    private Boolean isCorrect;
    private String fileUrl;
    @NotNull(message = "The option order must not be empty.")
    @Positive(message = "Option order can not be negative")
    private Integer optionOrder;
}
