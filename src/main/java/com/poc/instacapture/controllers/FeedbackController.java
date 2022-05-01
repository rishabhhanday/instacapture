package com.poc.instacapture.controllers;

import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.InstaMediaCachingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FeedbackController {
    private final InstaMediaCachingService instaMediaCachingService;

    @GetMapping("/comments/negative")
    public InstaMedia getMedia() {
        return instaMediaCachingService.getInstaMedia();
    }
}
