package com.example.bilingualb8.dto.responses.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OptionResponse {
    private Long id;
    private String title;
    private Boolean isCorrect;
    private Long questionId;
    private String fileUrl;
    private Integer optionOrder;
}
