package com.example.bilingualb8.dto.responses;

import lombok.Builder;

@Builder
public record SimpleResponse(
        String message
) {
}
