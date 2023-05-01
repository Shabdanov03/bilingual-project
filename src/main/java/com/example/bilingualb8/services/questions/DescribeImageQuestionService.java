package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;

public interface DescribeImageQuestionService {
    SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request);

    SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest);
}
