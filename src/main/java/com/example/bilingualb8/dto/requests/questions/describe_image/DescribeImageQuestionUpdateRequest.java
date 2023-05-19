package com.example.bilingualb8.dto.requests.questions.describe_image;

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
    private String file;
    private Boolean isActive;
}
