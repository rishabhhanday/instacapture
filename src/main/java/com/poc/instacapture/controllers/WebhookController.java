package com.poc.instacapture.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/webhooks")
public class WebhookController {

    @GetMapping("")
    public ResponseEntity<Integer> webhookVerification(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.challenge") Integer challenge,
            @RequestParam("hub.verify_token") String token) {
        log.info("webhook verification triggered, mode={}, challenge={}, token={}", mode, challenge, token);
        return ResponseEntity.ok(challenge);
    }

    @PostMapping("")
    public ResponseEntity<String> webhookSubscription(@RequestBody String request) {
        log.info("Webhook subscription triggered, requestBody={}", request);
        return ResponseEntity.ok(request);
    }
}
