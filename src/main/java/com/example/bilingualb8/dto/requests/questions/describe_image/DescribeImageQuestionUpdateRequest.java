package com.example.bilingualb8.dto.requests.questions.describe_image;

import com.example.bilingualb8.dto.requests.file.FileRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DescribeImageQuestionUpdateRequest {
    private String title;
    private String correctAnswer;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @Positive(message = "Order can not be negative")
    private Integer questionOrder;
    private FileRequest file;
    private Boolean isActive;
}
