package com.example.bilingualb8.dto.requests.questions.type_what_you_hear;

import com.example.bilingualb8.dto.requests.file.FileRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TypeWhatYouHearQuestionUpdateRequest {
    private String title;
    private Integer duration;
    private Integer numberOfReplays;
    private String correctAnswer;
    private FileRequest file;
}
