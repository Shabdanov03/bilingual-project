package com.example.bilingualb8.dto.responses.answer;

import com.example.bilingualb8.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserAnswerResponse {
    private Long questionId; // todo no need
    private AnswerStatus answerStatus;
    private String optionTitle;
}
