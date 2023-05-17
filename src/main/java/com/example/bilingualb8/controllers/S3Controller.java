package com.example.bilingualb8.controllers;

import com.example.bilingualb8.config.s3Config.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/s3-file")
@RequiredArgsConstructor
@CrossOrigin
public class S3Controller {
    private final S3Service s3Service;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is upload file method")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> uploadFile(@RequestParam MultipartFile multipartFile) throws IOException {
        return s3Service.upload(multipartFile);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This is delete file method")
    @DeleteMapping
    Map<String, String> deleteFile(@RequestParam String link) {
        return s3Service.delete(link);
    }
}
