package com.example.bilingualb8.dto.requests.questions.listen_and_select_english_words;

import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListenAndSelectEnglishWordsUpdateRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    private String correctAnswer;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    @Positive(message = "Number of Replays can not be negative")
    private Integer numberOfReplays;
    @Valid
    @NotNull(message = "The options must not be empty.")
    private List<OptionUpdateRequest> options;
    private Boolean isActive;
}
