package com.poc.instacapture.service.impl;

import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.responses.InstaMedia;
import com.poc.instacapture.service.InstaCommentApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class InstaCommentApiClientImpl implements InstaCommentApiClient {

    @Autowired
    private InstaProperties instaProperties;

    @Override
    public InstaMedia getMediaInformationByMediaId(String mediaId) {
        log.info("Calling API={}", instaProperties.getCommentUrl());

        return new RestTemplate()
                .getForObject(
                        instaProperties.getCommentUrl(),
                        InstaMedia.class,
                        instaProperties.getMediaId(),
                        instaProperties.getFields(),
                        instaProperties.getToken());
    }

    @Override
    public String deleteComment(String commentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(instaProperties.getToken());
        HttpEntity<?> httpEntity = new HttpEntity<>(null, headers);

        String deleteUrl = instaProperties.getFacebookUrl() + "/" + commentId;
        log.info("Delete comment, url=DELETE {}", deleteUrl);
        return new RestTemplate().exchange(deleteUrl, HttpMethod.DELETE, httpEntity, String.class).getBody();
    }
}
