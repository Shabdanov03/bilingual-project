package com.example.bilingualb8.dto.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotBlank(message = "The email must not be empty.")
        @NotNull(message = "The email must not be empty.")
        @Email(message = "Sorry, the email address you entered is invalid. Please check if it is correct")
        String email,

        @NotBlank(message = "The password must not be empty.")
        @NotNull(message = "The password must not be empty.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "The password must contain at least one letter in , one number and be at least 8 characters long.")
        String password
) {
}
