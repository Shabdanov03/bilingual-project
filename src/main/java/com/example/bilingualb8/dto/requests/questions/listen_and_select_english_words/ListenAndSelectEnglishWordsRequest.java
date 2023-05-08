package com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ListenAndSelectEnglishWordsRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "Number of replays  must not be empty.")
    @Positive(message = "Number of Replays can not be negative")
    private Integer numberOfReplays;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "Correct answer must not be empty")
    private String correctAnswer;
    @NotNull(message = "Option must not be empty")
    @Valid
    private List<OptionRequest> optionsRequest;
    @NotNull(message = "The isActive id must not be empty.")
    private Boolean isActive;

}
