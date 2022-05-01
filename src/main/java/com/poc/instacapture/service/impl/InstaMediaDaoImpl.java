package com.poc.instacapture.service.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.InstaMediaDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstaMediaDaoImpl implements InstaMediaDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public InstaMedia save(InstaMedia instaMedia) {
        dynamoDBMapper.save(instaMedia);
        return instaMedia;
    }
}
