package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface SelectBestTitleQuestionService {
    SimpleResponse save(SelectBestTitleQuestionRequest request);
    SimpleResponse update(Long id, SelectBestTitleQuestionUpdateRequest request);
}
