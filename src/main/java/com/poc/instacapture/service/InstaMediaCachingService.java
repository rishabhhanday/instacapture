package com.poc.instacapture.service;

import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.models.responses.CommentData;

public interface InstaMediaCachingService {
    boolean isCommentAddressed(CommentData commentsData);

    InstaMedia cache(InstaMedia instaMedia);

    InstaMedia getInstaMedia();
}
