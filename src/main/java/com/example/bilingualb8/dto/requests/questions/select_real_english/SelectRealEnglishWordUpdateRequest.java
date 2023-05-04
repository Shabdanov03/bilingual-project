package com.example.bilingualb8.dto.requests.questions.select_real_english;

import com.example.bilingualb8.dto.requests.option.OptionRequest;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectRealEnglishWordUpdateRequest {
    private String title;
    @Positive(message = "Test id can not be negative")
    private Integer duration;
    @Positive(message = "Question order can not be negative")
    Integer questionOrder;
    private List<OptionRequest> option;
}
