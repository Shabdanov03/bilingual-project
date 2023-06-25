package com.example.bilingualb8.dto.responses.answer;

import com.example.bilingualb8.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@ToString
public class UserAnswerResponse {
    private Long answerId;
    private Long questionId; // todo no need
    private AnswerStatus answerStatus;
    private String optionTitle;
    private String data;
    private String fileUrl;
    private Integer numberOfReplace;
}
