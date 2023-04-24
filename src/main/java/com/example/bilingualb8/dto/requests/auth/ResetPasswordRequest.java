package com.example.bilingualb8.dto.requests.auth;

import com.example.bilingualb8.validations.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ResetPasswordRequest(
        @NotBlank(message = "The password must not be empty.")
        @NotNull(message = "The password must not be empty.")
        @PasswordValid
        String newPassword
) {
}
