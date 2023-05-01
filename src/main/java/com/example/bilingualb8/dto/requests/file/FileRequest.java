package com.example.bilingualb8.dto.requests.file;

import com.example.bilingualb8.enums.FileType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileRequest {
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private FileType fileType = FileType.IMAGE;
    private String fileUrl;

}
