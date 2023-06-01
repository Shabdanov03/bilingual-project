package com.example.bilingualb8.dto.responses.result;

import com.example.bilingualb8.dto.responses.questions.ResultQuestionResponse;
import com.example.bilingualb8.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class EvaluatingSubmittedResultResponse {
    private Long resultId;
    private String userFullName;
    private String testTitle;
    private LocalDateTime dateOfSubmission;
    private float finalScore;
    private ResultStatus resultStatus;
    private List<ResultQuestionResponse> resultQuestionResponses;

    public EvaluatingSubmittedResultResponse(Long id, String userFullName, String testTitle, LocalDateTime dateOfSubmission, float finalScore, ResultStatus resultStatus) {
        this.resultId = id;
        this.userFullName = userFullName;
        this.testTitle = testTitle;
        this.dateOfSubmission = dateOfSubmission;
        this.finalScore = finalScore;
        this.resultStatus = resultStatus;
    }
}
