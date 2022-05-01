package com.poc.instacapture.schedulers;

import com.poc.instacapture.service.CaptureFeedbackWorkflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class CommentCaptureScheduler {
    @Autowired
    private CaptureFeedbackWorkflow captureFeedbackWorkflow;

    @Scheduled(fixedRate = 5000, initialDelay = 20000)
    public void cronJobScheduler() {
        log.info("Capture feedback flow started.");
        captureFeedbackWorkflow.captureFeedback();
        log.info("Capture feedback flow ended.");
    }
}
