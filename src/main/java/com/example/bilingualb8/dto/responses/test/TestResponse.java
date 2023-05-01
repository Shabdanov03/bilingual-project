package com.example.bilingualb8.dto.responses.test;

import com.example.bilingualb8.dto.responses.questions.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class TestResponse {
    private Long id;
    private Integer duration;
    private String title;
    private String shortDescription;
    private List<QuestionResponse> questions;
}

