package com.example.bilingualb8.dto.responses.test;

import lombok.Builder;

@Builder
public record TestListResponse(
        Integer duration,
        String title,
        String shortDescription
        ) {
}
