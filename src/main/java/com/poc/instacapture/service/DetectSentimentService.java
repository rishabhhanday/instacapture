package com.poc.instacapture.service;


import com.amazonaws.services.comprehend.model.DetectSentimentResult;

public interface DetectSentimentService {
    DetectSentimentResult detectSentiment(String comment);
}
