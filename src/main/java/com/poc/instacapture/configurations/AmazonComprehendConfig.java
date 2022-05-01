package com.poc.instacapture.configurations;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonComprehendConfig {
    @Value("${AWS_REGION}")
    private String region;

    @Bean
    public AmazonComprehend comprehendClient() {
        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

        return AmazonComprehendClientBuilder.standard()
                .withCredentials(awsCreds)
                .withRegion(region)
                .build();
    }
}
