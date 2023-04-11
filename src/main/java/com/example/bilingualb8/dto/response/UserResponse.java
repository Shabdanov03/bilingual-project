package com.example.bilingualb8.dto.response;

public record UserResponse(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
