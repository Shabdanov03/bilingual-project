package com.example.bilingualb8.dto.requests.questions.type_what_you_hear;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeWhatYouHearQuestionRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    private Integer questionOrder;
    @NotNull(message = "Number of replays  must not be empty.")
    @Positive(message = "Number of Replays can not be negative")
    private Integer numberOfReplays;
    @NotNull(message = "File must not be empty")
    private String fileRequest;
    @NotNull(message = "Correct answer must not be empty")
    private String correctAnswer;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The isActive  must not be empty.")
    private Boolean isActive;

    public TypeWhatYouHearQuestionRequest(String title, Integer duration, Integer questionOrder,
                                          Integer numberOfReplays, String fileRequest, String correctAnswer,
                                          Long testId, Boolean isActive) {
        this.title = title;
        this.duration = duration;
        this.questionOrder = questionOrder;
        this.numberOfReplays = numberOfReplays;
        this.fileRequest = fileRequest;
        this.correctAnswer = correctAnswer;
        this.testId = testId;
        this.isActive = isActive;
    }

    public TypeWhatYouHearQuestionRequest() {
    }
}
