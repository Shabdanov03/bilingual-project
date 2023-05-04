package com.example.bilingualb8.dto.requests.option;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionRequest {
    private Long id;
    private String title;
    private Boolean isCorrect;
    private String fileUrl;
}
