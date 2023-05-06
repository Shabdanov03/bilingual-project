package com.example.bilingualb8.dto.requests.questions.select_the_main_idea;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectTheMainIdeaQuestionRequest {
    @NotNull(message = "The title must not be empty !")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Question order can not be negative")
    private Integer questionOrder;

    @NotNull(message = "The options  must not be empty.")
    @Valid
    private List<OptionRequest> options;    @NotNull(message = "Passage answer must not be empty")
    private String passage;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;



}
