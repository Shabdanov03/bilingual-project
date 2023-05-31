package com.example.bilingualb8.services;

import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.result.EvaluatingSubmittedResultResponse;
import com.example.bilingualb8.dto.responses.result.SubmittedResultsResponse;
import com.example.bilingualb8.dto.responses.userResult.MyResultResponse;
import com.example.bilingualb8.entity.Answer;
import com.example.bilingualb8.entity.Result;
import com.example.bilingualb8.entity.Test;
import com.example.bilingualb8.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ResultService {
    List<MyResultResponse> getAll(Authentication authentication);

    SimpleResponse deleteById(Long id);
    Result createResult(Test test, User user, List<Answer> answers);
    List<SubmittedResultsResponse> getAll();
    EvaluatingSubmittedResultResponse getByIdEvaluatedSubmittedResult(Long resultId);
}
