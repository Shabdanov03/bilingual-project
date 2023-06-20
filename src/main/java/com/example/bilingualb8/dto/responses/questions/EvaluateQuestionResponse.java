package com.example.bilingualb8.dto.responses.questions;

import com.example.bilingualb8.dto.responses.answer.UserAnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateQuestionResponse {
    private String userFullName;
    private String testName;
    private float evaluationScore;
    private Long answerId;
    private QuestionResponse questionResponse;
    private List<UserAnswerResponse> userAnswerResponse;

    public EvaluateQuestionResponse(String userName, String testName, float evaluationScore,Long answerId) {
        this.userFullName = userName;
        this.testName = testName;
        this.evaluationScore = evaluationScore;
        this.answerId = answerId;
    }
}
