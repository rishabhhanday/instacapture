package com.poc.instacapture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InstaCaptureApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaCaptureApplication.class, args);
    }
}
