package de.rxbsi.streamnest.storage.video.service;

import de.rxbsi.streamnest.storage.genres.service.GenreEntity;
import de.rxbsi.streamnest.user.service.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "VIDEO")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @ManyToMany
    @JoinTable(
            name = "video_genre",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
