package de.rxbsi.streamnest.storage.genres.mapping;

import de.rxbsi.streamnest.storage.genres.service.GenreEntity;
import de.rxbsi.streamnest.storage.genres.service.dto.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreEntity toEntity(Genre dto);
    Genre toDto(GenreEntity entity);
}

