package com.poc.instacapture.service.impl;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.poc.instacapture.service.DetectSentimentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetectSentimentServiceImpl implements DetectSentimentService {
    private final AmazonComprehend comprehendClient;

    @Override
    public DetectSentimentResult detectSentiment(String comment) {

        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest()
                .withText(comment)
                .withLanguageCode("en");
        return comprehendClient.detectSentiment(detectSentimentRequest);
    }
}
