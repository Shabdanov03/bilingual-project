package com.example.bilingualb8.dto.requests.score;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ScoreSenderRequest {
    @NotBlank(message = "The email must not be empty.")
    @NotNull(message = "The email must not be empty.")
    @Email(message = "Sorry, the email address you entered is invalid. Please check if it is correct")
    private String email;
    private String subject;
    private String message;
}
