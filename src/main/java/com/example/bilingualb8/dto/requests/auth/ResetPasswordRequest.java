package com.example.bilingualb8.dto.requests.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record ResetPasswordRequest(
        @NotBlank(message = "The password must not be empty.")
        @NotNull(message = "The password must not be empty.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
                message = "The password must contain at least one letter in , one number and be at least 8 characters long.")
        String newPassword
) {
}
