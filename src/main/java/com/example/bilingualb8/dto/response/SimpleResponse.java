package com.example.bilingualb8.dto.response;

import org.springframework.http.HttpStatus;

public record SimpleResponse(
        HttpStatus status,
        String massage
) {
}
