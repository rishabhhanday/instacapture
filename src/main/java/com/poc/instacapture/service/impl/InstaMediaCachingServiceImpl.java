package com.poc.instacapture.service.impl;

import com.poc.instacapture.models.responses.CommentData;
import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.InstaMediaCachingService;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class InstaMediaCachingServiceImpl implements InstaMediaCachingService {
    @Getter
    private InstaMedia instaMedia;

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
}
