package com.poc.instacapture.service;


import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;

public interface DetectSentimentService {
    DetectSentimentResponse detectSentiment();
}
