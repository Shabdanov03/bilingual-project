package com.example.bilingualb8.dto.requests.file;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileRequest {
    private String fileUrl;
}
