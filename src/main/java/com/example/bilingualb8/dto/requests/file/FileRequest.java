package com.example.bilingualb8.dto.requests.file;

import com.example.bilingualb8.enums.FileType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record FileRequest(
        @Enumerated(EnumType.STRING)
        FileType fileType,
        String fileUrl,
        Long questionId
) {
}
