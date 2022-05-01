package com.poc.instacapture.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.responses.CommentData;
import com.poc.instacapture.models.responses.InstaMedia;
import com.poc.instacapture.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.comprehend.model.DetectSentimentResponse;
import software.amazon.awssdk.services.comprehend.model.SentimentType;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CaptureFeedbackWorkflowImpl implements CaptureFeedbackWorkflow {
    private final InstaProperties instaProperties;
    private final InstaCommentApiClient instaCommentApiClient;
    private final InstaMediaCachingService instaMediaCachingService;
    private final DetectSentimentService detectSentimentService;
    private final InstaMediaDao instaMediaDao;
    private final ObjectMapper objectMapper;

    @Override
    public void captureFeedback() {
        String latestMediaId = instaProperties.getMediaId();

        InstaMedia instaMediaResponse = instaCommentApiClient
                .getMediaInformationByMediaId(latestMediaId);

        log.info("total comments={} on ID={}",
                instaMediaResponse.getComments().getData().size(),
                instaMediaResponse.getId());

        CommentData commentData = instaMediaResponse
                .getComments()
                .getData()
                .stream()
                .findFirst()
                .orElse(null);

        log.info("checking if comment was already addressed.");
        if (commentData != null && !instaMediaCachingService.isCommentAddressed(commentData)) {
            DetectSentimentResponse detectSentimentResponse = detectSentimentService.detectSentiment();

            log.info("Checking if the sentiment of comment is negative, comment={}", commentData.getText());
            if (detectSentimentResponse.sentiment().equals(SentimentType.NEGATIVE)) {
                log.info("Negative feedback detected");
                com.poc.instacapture.models.dao.CommentData comment = objectMapper
                        .convertValue(commentData, com.poc.instacapture.models.dao.CommentData.class);

                comment.setSentimentScore(String.valueOf(detectSentimentResponse.sentimentScore().negative()));

                com.poc.instacapture.models.dao.InstaMedia instaMedia = instaMediaCachingService.getInstaMedia() == null
                        ? objectMapper.convertValue(instaMediaResponse, com.poc.instacapture.models.dao.InstaMedia.class)
                        : instaMediaCachingService.getInstaMedia();

                List<com.poc.instacapture.models.dao.CommentData> data = instaMedia.getComments().getData();
                data.add(comment);

                log.info("caching the response.");
                instaMediaCachingService.cache(instaMedia);
                instaMediaDao.save(instaMedia);
            }
        }
    }
}
