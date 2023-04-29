package com.example.bilingualb8.repositories.custom;

import com.example.bilingualb8.dto.responses.questions.describe_image.DescribeImageQuestionResponse;
import java.util.List;
import java.util.Optional;

public interface CustomDescribeImageQuestionRepository {
    List<DescribeImageQuestionResponse> getAllDescribeImageQuestion();

    Optional<DescribeImageQuestionResponse> getDescribeImageQuestionById(Long id);
}
