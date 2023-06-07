package com.example.bilingualb8.dto.requests.questions.type_what_you_hear;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TypeWhatYouHearQuestionUpdateRequest {
    private String title;
    private Integer duration;
    private Integer numberOfReplays;
    private String correctAnswer;
    private String file;
    private Boolean isActive;

    public TypeWhatYouHearQuestionUpdateRequest(
            String title, Integer duration, Integer numberOfReplays,
            String correctAnswer, String file, Boolean isActive) {
        this.title = title;
        this.duration = duration;
        this.numberOfReplays = numberOfReplays;
        this.correctAnswer = correctAnswer;
        this.file = file;
        this.isActive = isActive;
    }

    public TypeWhatYouHearQuestionUpdateRequest() {
    }
}
