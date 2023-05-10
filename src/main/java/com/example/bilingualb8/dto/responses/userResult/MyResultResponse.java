package com.example.bilingualb8.dto.responses.userResult;

import com.example.bilingualb8.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class MyResultResponse {
    private Long id;
    private LocalDate dateOfSubmission;
    private String testName;
    private ResultStatus resultStatus;
    private float score;
}
