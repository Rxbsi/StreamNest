package de.rxbsi.streamnest.storage.video.service.dto;

import de.rxbsi.streamnest.storage.genres.service.dto.Genre;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class Video {
    private Long id;
    private String title;
    private String description;
    private String pictureUrl;
    private Set<Genre> genres;
    private LocalDate releaseDate;
    private LocalDate uploadDate;
}
