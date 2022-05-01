package com.poc.instacapture;

import org.junit.jupiter.api.Test;

public class POCTest {
    @Test
    public void testThis(){
        String MEDIA_URL = "https://graph.facebook.com/v13.0/$MEDIA_ID_INPUT$?fields=caption%2Cid%2Cmedia_url%2Cthumbnail_url%2Ccomments%7Bid%2Ctext%2Cusername%7D&access_token=$ACCESS_TOKEN_INPUT$";
        String replace = MEDIA_URL.replace("$MEDIA_ID_INPUT$", "123").replace("$ACCESS_TOKEN_INPUT$", "***ACCESS_TOKEN");

        System.out.println(replace);
    }
}
