package com.poc.instacapture.service.impl;

import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.responses.InstaMedia;
import com.poc.instacapture.service.InstaCommentApiClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class InstaCommentApiClientImpl implements InstaCommentApiClient {
    private final InstaProperties instaProperties;
    private final RestTemplate restTemplate;

    @Override
    public InstaMedia getMediaInformationByMediaId(String mediaId) {
        log.info("Calling API={}", instaProperties.getCommentUrl());

        return restTemplate
                .getForObject(
                        instaProperties.getCommentUrl(),
                        InstaMedia.class,
                        instaProperties.getMediaId(),
                        instaProperties.getFields(),
                        instaProperties.getToken());
    }

    @Override
    public String deleteComment(String commentId) {
        String deleteUrl = instaProperties.getFacebookUrl() + "/" + commentId;
        log.info("Delete comment, url=DELETE {}", deleteUrl);

        return restTemplate.exchange(deleteUrl, HttpMethod.DELETE, getHttpEntity(), String.class).getBody();
    }

    @Override
    public String replyOnComment(String commentId, String reply) {
        String replyUrl = instaProperties.getFacebookUrl() + "/" + commentId + "/replies?message=" + reply;
        log.info("replying on comment, url=POST {}", replyUrl);

        return restTemplate.exchange(replyUrl, HttpMethod.POST, getHttpEntity(), String.class).getBody();
    }

    private HttpEntity<?> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(instaProperties.getToken());

        return new HttpEntity<>(null, headers);
    }
}
