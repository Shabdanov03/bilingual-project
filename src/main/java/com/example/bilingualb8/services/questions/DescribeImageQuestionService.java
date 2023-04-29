package com.example.bilingualb8.services.questions;

import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import com.example.bilingualb8.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import com.example.bilingualb8.dto.responses.SimpleResponse;
import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;

import java.util.List;

public interface DescribeImageQuestionService {
    SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request);

    List<DescribeImageQuestionResponse> getAllDescribeImageQuestion();

    DescribeImageQuestionResponse getDescribeImageQuestionById(Long id);

    SimpleResponse deleteDescribeImageQuestionById(Long id);

    SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest);
}
