package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.answer.UserAnswerResponse;

import java.util.List;

public interface CustomAnswerRepository {
    List<UserAnswerResponse> getAnswerResponsesByResultId(Long resultId);
    List<UserAnswerResponse> getAnswerResponsesByQuestionId(Long questionId);
}
