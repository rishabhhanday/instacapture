package com.poc.instacapture.service.impl;

import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.responses.CommentData;
import com.poc.instacapture.models.responses.InstaMedia;
import com.poc.instacapture.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

        if (instaMediaResponse.getComments() == null) {
            return;
        }

        CommentData commentData = instaMediaResponse
                .getComments()
                .getData()
                .stream()
                .findFirst()
                .orElse(null);

        log.info("checking if comment was already addressed.");
        if (commentData != null && !instaMediaCachingService.isCommentAddressed(commentData)) {
            DetectSentimentResult detectSentimentResult = detectSentimentService
                    .detectSentiment(commentData.getText());

            log.info("Checking if the sentiment of comment is negative, comment={}", commentData.getText());
            if (detectSentimentResult.getSentiment().equals("NEGATIVE")) {
                log.info("Negative feedback detected");
                com.poc.instacapture.models.dao.CommentData comment = objectMapper
                        .convertValue(commentData, com.poc.instacapture.models.dao.CommentData.class);

                comment.setSentimentScore(String.valueOf(detectSentimentResult.getSentimentScore().getNegative()));
                comment.setAddressed(false);

                com.poc.instacapture.models.dao.InstaMedia instaMedia;
                if (instaMediaCachingService.getInstaMedia() == null) {
                    instaMedia = objectMapper.convertValue(instaMediaResponse, com.poc.instacapture.models.dao.InstaMedia.class);
                    instaMedia.getComments().setData(Collections.singletonList(comment));
                } else {
                    instaMedia = instaMediaCachingService.getInstaMedia();
                    List<com.poc.instacapture.models.dao.CommentData> data =
                            new ArrayList<>(instaMedia.getComments().getData());
                    data.add(comment);
                    instaMedia.getComments().setData(data);
                }

                log.info("caching the response.");
                instaMediaCachingService.cache(instaMedia);
                instaMediaDao.save(instaMedia);
            }
        }
    }

    @Override
    public com.poc.instacapture.models.dao.InstaMedia removeComment(String commentId) {
        instaCommentApiClient.deleteComment(commentId);
        com.poc.instacapture.models.dao.InstaMedia instaMedia = instaMediaCachingService.removeCommentFromCache(commentId);
        instaMediaDao.save(instaMedia);
        return instaMedia;
    }


}
