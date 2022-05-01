package com.poc.instacapture.models.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class CommentData {
    private String id;
    private String text;
    private String username;
    private String timestamp;
    private String sentimentScore;
}
