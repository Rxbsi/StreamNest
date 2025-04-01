package de.rxbsi.streamnest.storage.genres.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Genre {
    private Long id;
    private Genres genre;

}
