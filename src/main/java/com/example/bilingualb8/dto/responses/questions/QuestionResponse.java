package com.example.bilingualb8.dto.responses.questions;

import com.example.bilingualb8.dto.responses.file.FileResponse;
import com.example.bilingualb8.dto.responses.option.OptionResponse;
import com.example.bilingualb8.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private Long id;
    private String title;
    private String statement;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.DESCRIBE_IMAGE;
    private Integer duration;
    private Integer minWords;
    private Integer numberOfReplays;
    private String correctAnswer;
    private String passage;
    private String audioText;
    private Long testId;
    private List<FileResponse> files;
    private List<OptionResponse> options;
    private Boolean isActive;
}
