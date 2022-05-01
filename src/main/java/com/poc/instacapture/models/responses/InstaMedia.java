package com.poc.instacapture.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstaMedia {
    private String caption;
    private String id;
    @JsonProperty("media_url")
    private String mediaUrl;
    private InstaMediaComment comments;
    @JsonProperty("comments_count")
    private Integer commentsCount;
    private String timestamp;
}
