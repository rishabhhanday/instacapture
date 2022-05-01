package com.poc.instacapture.service;

import com.poc.instacapture.models.dao.InstaMedia;

public interface CaptureFeedbackWorkflow {
    void captureFeedback();

    InstaMedia removeComment(String commentId);

    InstaMedia replyOnComment(String commentId, String reply);

    InstaMedia emailComment(String commentId, String emailText);
}
