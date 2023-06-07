package com.example.bilingualb8.dto.requests.questions.select_real_english;

import com.example.bilingualb8.dto.requests.option.OptionUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SelectRealEnglishWordUpdateRequest {
    private String title;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    private Integer questionOrder;
    @NotNull(message = "The Option must not be empty.")
    @Valid
    private List<OptionUpdateRequest> options;

    public SelectRealEnglishWordUpdateRequest() {
    }
}
