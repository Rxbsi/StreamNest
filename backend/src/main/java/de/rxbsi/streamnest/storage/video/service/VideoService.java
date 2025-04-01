package de.rxbsi.streamnest.storage.video.service;

import de.rxbsi.streamnest.storage.video.service.dto.Video;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {

    List<Video> listAll();

    Video createVideo(Video video);

    ResponseEntity<?> deleteVideo(Video video);

}
