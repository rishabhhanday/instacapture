package com.poc.instacapture.controllers;

import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.CaptureFeedbackWorkflow;
import com.poc.instacapture.service.InstaMediaCachingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public InstaMedia removeComment(@PathVariable(value = "id") String id) {
        return captureFeedbackWorkflow.removeComment(id);
    }

    @PostMapping("/comments/{id}/replies")
    public InstaMedia replyOnComment(@PathVariable(value = "id") String id, @RequestParam String message) {
        return captureFeedbackWorkflow.replyOnComment(id, message);
    }

    @PostMapping("/comments/{id}/email")
    public InstaMedia emailComment(@PathVariable(value = "id") String id, @RequestParam String message) {
        return captureFeedbackWorkflow.emailComment(id, message);
    }
}
