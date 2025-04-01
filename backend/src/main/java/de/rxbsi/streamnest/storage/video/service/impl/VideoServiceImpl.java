package de.rxbsi.streamnest.storage.video.service.impl;

import de.rxbsi.streamnest.storage.genres.mapping.GenreMapper;
import de.rxbsi.streamnest.storage.genres.service.GenreEntity;
import de.rxbsi.streamnest.storage.video.mapping.VideoMapper;
import de.rxbsi.streamnest.storage.video.service.VideoEntity;
import de.rxbsi.streamnest.storage.video.service.VideoService;
import de.rxbsi.streamnest.storage.video.service.dto.Video;
import de.rxbsi.streamnest.storage.video.service.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper mapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private VideoRepository repository;

    @Override
    public List<Video> listAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Video createVideo(Video video) {
        var videoEntity = new VideoEntity();

        videoEntity.setTitle(video.getTitle());
        videoEntity.setDescription(video.getDescription());

        videoEntity.setPictureUrl(video.getPictureUrl());

        Set<GenreEntity> genreEntities = video.getGenres().stream()
                .map(genre -> genreMapper.toEntity(genre))
                .collect(Collectors.toSet());
        videoEntity.setGenres(genreEntities);

        videoEntity.setReleaseDate(video.getReleaseDate());
        videoEntity.setUploadDate(LocalDate.now());

        repository.save(videoEntity);

        return mapper.toDto(videoEntity);
    }

    @Override
    public ResponseEntity<?> deleteVideo(Video video) {
        VideoEntity entity = mapper.toEntity(video);
        repository.delete(entity);
        return ResponseEntity.ok("Deleted Video");
    }
}
