package com.poc.instacapture.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "insta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstaProperties {
    private String token;
    private String mediaId;
    private String commentUrl;
    private String fields;
    private String facebookUrl;
}
