package com.example.bilingualb8.dto.requests.questions.select_best_title;

import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SelectBestTitleQuestionUpdateRequest {
    private String title;
    private Integer duration;
    private Integer questionOrder;
    private String passage;
    @Valid
    private List<OptionUpdateRequest> options;
}
