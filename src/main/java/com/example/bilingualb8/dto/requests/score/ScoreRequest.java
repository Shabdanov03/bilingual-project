package com.example.bilingualb8.dto.requests.score;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreRequest {
    @NotNull(message = "The score must not be empty.")
    private float score;
}
