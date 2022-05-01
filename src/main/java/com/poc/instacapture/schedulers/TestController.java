package com.poc.instacapture.schedulers;

import com.poc.instacapture.models.dao.InstaMedia;
import com.poc.instacapture.service.InstaMediaDao;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final InstaMediaDao instaMediaDao;

    @GetMapping("/save")
    public ResponseEntity<InstaMedia> instaMediaSave() {
        InstaMedia instaMedia = new InstaMedia();
        instaMedia.setId("123");

        return ResponseEntity.ok(instaMediaDao.save(instaMedia));
    }
}
