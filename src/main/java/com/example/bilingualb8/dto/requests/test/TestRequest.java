package com.example.bilingualb8.dto.requests.test;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record TestRequest(
        @NotNull(message = "The title must not be empty.")
        @Length(min = 5, max = 70)
        String title,
        @NotNull(message = "The description must not be empty.")
        String shortDescription,
        @NotNull(message = "The isActive must not be empty.")
        Boolean isActive
) {
}
