package com.example.bilingualb8.dto.requests.option;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The Is correct must not be empty.")
    private Boolean isCorrect;
    @NotNull(message = "The file url must not be empty.")
    private String fileUrl;
    @NotNull(message = "The Option order must not be empty.")
    @Positive(message = "Option order can not be negative")
    private Integer optionOrder;
}
