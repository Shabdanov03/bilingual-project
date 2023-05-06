package com.example.bilingualb8.dto.requests.questions.select_the_main_idea;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class SelectTheMainIdeaQuestionUpdateRequest {
    @NotNull(message = "The title must not be empty !")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    private Integer questionOrder;
    @Valid
    private List<OptionUpdateRequest> options;
    @NotNull(message = "Passage answer must not be empty")
    private String passage;
}
