package com.example.bilingualb8.dto.responses.file;

import com.example.bilingualb8.enums.FileType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FileResponse {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
    private String fileUrl;
    private Long questionId;

}
