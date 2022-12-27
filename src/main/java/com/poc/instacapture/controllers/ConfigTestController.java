package com.poc.instacapture.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigTestController {

    @Value("${test.config}")
    private String testConfig;

    @GetMapping("/test")
    public String getTestConfig() {
        return testConfig;
    }
}
