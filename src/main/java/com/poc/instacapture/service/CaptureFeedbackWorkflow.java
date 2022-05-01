package com.poc.instacapture.service;

import com.poc.instacapture.models.dao.InstaMedia;

public interface CaptureFeedbackWorkflow {
    void captureFeedback();
    InstaMedia removeComment(String commentId);
}
