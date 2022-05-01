package com.poc.instacapture.controllers;

import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.CaptureFeedbackWorkflow;
import com.poc.instacapture.service.InstaMediaCachingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FeedbackController {
    private final InstaMediaCachingService instaMediaCachingService;
    private final CaptureFeedbackWorkflow captureFeedbackWorkflow;

    @GetMapping("/comments/")
    public InstaMedia getMedia() {
        captureFeedbackWorkflow.captureFeedback();
        return instaMediaCachingService.getInstaMedia();
    }

    @DeleteMapping("/comments/{id}")
    public InstaMedia getLatestNegativeMedia(@PathVariable(value = "id") String id) {
        return captureFeedbackWorkflow.removeComment(id);
    }
}
