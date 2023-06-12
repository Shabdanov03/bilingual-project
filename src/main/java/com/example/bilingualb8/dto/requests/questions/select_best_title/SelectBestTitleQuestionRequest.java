package com.example.bilingualb8.dto.requests.questions.select_best_title;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class SelectBestTitleQuestionRequest {
    @NotNull(message = "The title order must not be empty.")
    private String title;
    @NotNull(message = "The duration order must not be empty.")
    @Positive(message = "Option order can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Question order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The test id  must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The passage order must not be empty.")
    private String passage;
    @NotNull(message = "The options  must not be empty.")
    @Valid
    private List<OptionRequest> options;
    @NotNull(message = "The isActive id must not be empty.")
    private Boolean isActive;

    public SelectBestTitleQuestionRequest() {

    }
}
