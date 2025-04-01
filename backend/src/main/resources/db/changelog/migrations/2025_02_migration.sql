-- liquibase formatted sql

-- changeset robin.franz:adding-video

CREATE TABLE GENRES
(
    ID          BIGSERIAL PRIMARY KEY,
    GENRE       VARCHAR(50) NOT NULL
);

CREATE TABLE VIDEO
(
    ID              BIGSERIAL PRIMARY KEY,
    TITLE           VARCHAR(255),
    DESCRIPTION     VARCHAR(255),
    PICTURE_URL     VARCHAR(255),
    RELEASE_DATE    DATE,
    UPLOAD_DATE     DATE,
    USER_ID         BIGINT
);

CREATE TABLE VIDEO_GENRE
(
    video_id    BIGINT,
    genre_id    BIGINT,

    FOREIGN KEY (video_id) REFERENCES VIDEO(id),
    FOREIGN KEY (genre_id) REFERENCES GENRES(id),
    PRIMARY KEY (video_id, genre_id)
);