package com.poc.instacapture.service;

import com.poc.instacapture.models.responses.InstaMedia;

public interface InstaCommentApiClient {
    InstaMedia getMediaInformationByMediaId(String mediaId);

    String deleteComment(String commentId);

    String replyOnComment(String commentId, String reply);
}
