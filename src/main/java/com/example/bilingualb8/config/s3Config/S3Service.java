package com.example.bilingualb8.config.s3Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3;
    @Value("${AWS_BUCKET_PATH}")
    private String BUCKET_PATH;
    @Value("${AWS_BUCKET_NAME}")
    private String BUCKET_NAME;

    public Map<String, String> upload(MultipartFile file) throws IOException {
        String key = System.currentTimeMillis() + file.getOriginalFilename();
        PutObjectRequest put = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .contentType("jpeg")
                .contentType("png")
                .contentType("ogg")
                .contentType("mp3")
                .contentType("mpeg")
                .contentType("ogg")
                .contentType("m4a")
                .contentType("oga")
                .contentType("JPG")
                .contentLength(file.getSize())
                .key(key)
                .build();
        s3.putObject(put, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return Map.of(
                "link", BUCKET_PATH + key);
    }

    public Map<String, String> delete(String fileLink) {

        log.info("Deleting file...");

        try {
            String key = fileLink.substring(BUCKET_PATH.length());
            log.warn("Deleting object: {}", key);

            s3.deleteObject(dor -> dor.bucket(BUCKET_NAME).key(key).build()); //dor = DeleteObjectRequest

        } catch (S3Exception e) {
            throw new IllegalStateException(e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
        return Map.of(
                "message", fileLink + " has been deleted");
    }

}
