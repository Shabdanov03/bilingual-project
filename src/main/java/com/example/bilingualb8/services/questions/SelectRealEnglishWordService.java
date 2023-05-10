package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import com.example.bilingualb8.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface SelectRealEnglishWordService {
    SimpleResponse saveSelectRealEnglishWordQuestion(SelectRealEnglishWordRequest request);
    SimpleResponse updateSelectRealEnglishWordQuestion(Long id,SelectRealEnglishWordUpdateRequest request);
}
