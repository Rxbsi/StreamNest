package de.rxbsi.streamnest.storage.video.resource;

import de.rxbsi.streamnest.storage.video.service.VideoService;
import de.rxbsi.streamnest.storage.video.service.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping("/listAll")
    public List<Video> listAllVideos() {
        return service.listAll();
    }

    @PostMapping("/addVideo")
    public Video addVideo(@RequestBody Video video) {
        return service.createVideo(video);
    }

    @DeleteMapping("/deleteVideo")
    public ResponseEntity<?> deleteVideo(Video video) {
        return service.deleteVideo(video);
    }

}
