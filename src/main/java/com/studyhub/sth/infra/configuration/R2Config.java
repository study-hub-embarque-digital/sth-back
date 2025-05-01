package com.studyhub.sth.infra.configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class R2Config {

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("ef231db429e49b1c8acc8195ef3e5820", "6525dd8839565573473d0350f4991f3ebab3a687697f95e72ac4c3c7a757aa8c");

        return S3Client.builder()
                .region(Region.US_EAST_1) // Região fictícia
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(URI.create("https://83d9351fbe0e04bb88114471abd9b1aa.r2.cloudflarestorage.com"))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }
}
