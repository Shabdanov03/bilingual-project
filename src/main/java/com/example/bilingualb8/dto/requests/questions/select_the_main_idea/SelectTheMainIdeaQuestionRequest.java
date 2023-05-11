package com.example.bilingualb8.dto.requests.questions.select_the_main_idea;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectTheMainIdeaQuestionRequest {
    private String title;
    private Integer duration;
    private Integer questionOrder;
    private String passage;
    @Valid
    private List<OptionRequest> options;
    private Long testId;


}
