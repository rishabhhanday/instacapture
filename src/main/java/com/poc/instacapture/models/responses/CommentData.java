package com.poc.instacapture.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentData {
    private String id;
    private String text;
    private String username;
    private String timestamp;
}
