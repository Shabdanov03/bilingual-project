package com.example.bilingualb8.dto.request;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
