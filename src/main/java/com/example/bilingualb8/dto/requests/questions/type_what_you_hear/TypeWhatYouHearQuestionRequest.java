package com.example.bilingualb8.dto.requests.questions.type_what_you_hear;
import com.example.bilingualb8.dto.requests.file.FileRequest;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TypeWhatYouHearQuestionRequest {
    @NotNull(message = "The title must not be empty.")
    private String  title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.TYPE_WHAT_YOU_HEAR;
    @NotNull(message = "Number of replays  must not be empty.")
    private Integer numberOfReplays;
    @NotNull(message = "File must not be empty")
    private FileRequest fileRequest;
    @NotNull(message = "Correct answer must not be empty")
    private String  correctAnswer;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
}
