package com.poc.instacapture.service.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.models.responses.CommentData;
import com.poc.instacapture.service.InstaMediaCachingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstaMediaCachingServiceImpl implements InstaMediaCachingService {
    @Getter
    private InstaMedia instaMedia;
    @Autowired
    private DynamoDBMapper dynamoDBMapper;
    @Autowired
    private InstaProperties instaProperties;

    @PostConstruct
    void cacheInstaMedia() {
        this.instaMedia = dynamoDBMapper.load(InstaMedia.class, instaProperties.getMediaId());
    }

    @Override
    public boolean isCommentAddressed(CommentData commentsData) {
        if (instaMedia == null || commentsData == null) {
            return false;
        } else {
            return instaMedia
                    .getComments()
                    .getData()
                    .stream()
                    .anyMatch(cachedComment -> cachedComment.getId().equals(commentsData.getId()));
        }
    }

    @Override
    public InstaMedia cache(InstaMedia instaMedia) {
        this.instaMedia = instaMedia;
        return this.instaMedia;
    }

    @Override
    public InstaMedia removeCommentFromCache(String commentId) {
        List<com.poc.instacapture.models.dao.CommentData> data = instaMedia.getComments().getData();
        List<com.poc.instacapture.models.dao.CommentData> updateComments = data
                .stream()
                .filter((commentData -> !commentData.getId().equals(commentId)))
                .collect(Collectors.toList());

        instaMedia.getComments().setData(updateComments);

        return instaMedia;
    }

    @Override
    public InstaMedia addressComment(String commentId) {
        for (com.poc.instacapture.models.dao.CommentData commentData : instaMedia.getComments().getData()) {
            if (commentData.getId().equals(commentId)) {
                commentData.setAddressed(true);
            }
        }

        return instaMedia;
    }
}
