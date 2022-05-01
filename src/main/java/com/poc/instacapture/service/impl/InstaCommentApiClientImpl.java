package com.poc.instacapture.service.impl;

import com.poc.instacapture.configurations.InstaProperties;
import com.poc.instacapture.models.responses.InstaMedia;
import com.poc.instacapture.service.InstaCommentApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
