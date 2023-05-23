package com.example.bilingualb8.dto.requests.file;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FileRequest {
    @NotNull(message = "File Url can not be null")
    private String fileUrl;
    private String test;
}
