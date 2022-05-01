package com.poc.instacapture.models.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "instaMedia")
public class InstaMedia {
    private String caption;
    @DynamoDBHashKey
    private String id;
    @JsonProperty("media_url")
    private String mediaUrl;
    private InstaMediaComment comments;
    @JsonProperty("comments_count")
    private Integer commentsCount;
}
