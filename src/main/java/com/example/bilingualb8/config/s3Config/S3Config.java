package com.example.bilingualb8.config.s3Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Getter
@Setter
public class S3Config {
    @Value("${AWS_ACCESS_KEY}")
    private String AWS_ACCESS_KEY;
    @Value("${AWS_SECRET_KEY}")
    private String AWS_SECRET_KEY;
    @Value("${AWS_REGION}")
    private String REGION;

    @Bean
    S3Client s3Client() {
        Region region = Region.of(REGION);
        final AwsBasicCredentials credentials =
                AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        return S3Client.builder().region(region).
                credentialsProvider(StaticCredentialsProvider
                        .create(credentials)).build();
    }
}
