package com.example.bilingualb8.dto.requests.option;

import com.example.bilingualb8.entity.Option;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class OptionRequest {
    @NotNull(message = "Option Title can not be null")
    private String title;
    @NotNull(message = "Is Correct can not be null")
    private Boolean isCorrect;
    private String fileUrl;
    @NotNull(message = "The option order must not be empty.")
    @Positive(message = "Option order can not be negative")
    private Integer optionOrder;

    public OptionRequest() {
    }
}
