package com.example.bilingualb8.dto.responses.answer;

import com.example.bilingualb8.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class UserAnswerResponse {
    private Long answerId;
    private Long questionId; // todo no need
    private AnswerStatus answerStatus;
    private String optionTitle;
    private String data;
    private String fileUrl;
    private Integer numberOfReplace;

    public UserAnswerResponse(Long answerId, Long questionId, AnswerStatus answerStatus, String optionTitle, String data, Integer numberOfReplace) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerStatus = answerStatus;
        this.optionTitle = optionTitle;
        this.data = data;
        this.numberOfReplace = numberOfReplace;
    }

    public UserAnswerResponse(Long answerId, Long questionId, AnswerStatus answerStatus, String data, Integer numberOfReplace) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerStatus = answerStatus;
        this.data = data;
        this.numberOfReplace = numberOfReplace;
    }
    public UserAnswerResponse(Long answerId, Long questionId, AnswerStatus answerStatus, Integer numberOfReplace) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerStatus = answerStatus;
        this.numberOfReplace = numberOfReplace;
    }
}
