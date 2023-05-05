package com.example.bilingualb8.dto.requests.questions.select_real_english;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectRealEnglishWordRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Test question order can not be negative")
    Integer questionOrder;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The option must not be empty.")
    @Valid
    private List<OptionRequest> option;
}
