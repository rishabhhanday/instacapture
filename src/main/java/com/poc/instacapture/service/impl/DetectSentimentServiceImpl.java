package com.poc.instacapture.service.impl;

import com.poc.instacapture.service.DetectSentimentService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;
import software.amazon.awssdk.services.comprehend.model.SentimentScore;
import software.amazon.awssdk.services.comprehend.model.SentimentType;

@Service
public class DetectSentimentServiceImpl implements DetectSentimentService {
    @Override
    public DetectSentimentResponse detectSentiment() {
        return DetectSentimentResponse
                .builder()
                .sentiment(SentimentType.NEGATIVE)
                .sentimentScore(SentimentScore.builder().negative(95F).positive(2F).mixed(1F).neutral(2F).build())
                .build();
    }
}
