package com.example.bilingualb8.dto.requests.test;

import com.example.bilingualb8.dto.requests.answer.AnswerRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PassTestRequest {
    @NotNull(message = "User ID can not be null")
    @Positive(message = "User id can not be negative")
    private Long userId;
    @NotNull(message = "Answers can not be null")
    @Valid
    private List<AnswerRequest> answers;
}