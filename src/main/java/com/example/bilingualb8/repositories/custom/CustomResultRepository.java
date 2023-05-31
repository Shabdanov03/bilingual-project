package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.result.EvaluatingSubmittedResultResponse;
import com.example.bilingualb8.dto.responses.result.SubmittedResultsResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;

import java.util.List;

public interface CustomResultRepository {
    List<MyResultResponse> getAll(Long userId);
    List<SubmittedResultsResponse> getAllSubmittedResults();
    EvaluatingSubmittedResultResponse getByIdEvaluatingSubmittedResult(Long resultId);
    Long getResultIdByAnswerId(Long answerId);
}
