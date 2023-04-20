package com.example.bilingualb8.dto.requests.test;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TestRequest (
        @NotBlank(message = "The title must not be empty.")
        @NotNull(message = "The title must not be empty.")
        String title,
        @NotBlank(message = "The description must not be empty.")
        @NotNull(message = "The description must not be empty.")
        String shortDescription
){
}
