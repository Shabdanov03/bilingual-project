package com.example.bilingualb8.dto.requests.questions.describe_image;

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
public class DescribeImageQuestionRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The correct answer must not be empty.")
    private String correctAnswer;
    @Builder.Default
    @NotNull(message = "The question type must not be empty.")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.DESCRIBE_IMAGE;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The file must not be empty.")
    private FileRequest file;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
}
