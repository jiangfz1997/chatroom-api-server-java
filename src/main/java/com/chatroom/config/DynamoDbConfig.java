package com.chatroom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    @Value("${aws.dynamodb.endpoint}")
    private String endpoint;

    @Value("${aws.dynamodb.region}")
    private String region;

    /**
     * Configures and exposes a DynamoDbClient bean.
     *
     * Uses a local endpoint for development (LocalStack / DynamoDB Local).
     * In production, remove the endpointOverride and use IAM roles instead
     * of static credentials.
     */
    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.of(region))
                .credentialsProvider(
                        // Static credentials are only for local development.
                        // Production should use DefaultCredentialsProvider (IAM roles).
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create("fakeMyKeyId", "fakeSecretAccessKey")
                        )
                )
                .build();
    }
}
