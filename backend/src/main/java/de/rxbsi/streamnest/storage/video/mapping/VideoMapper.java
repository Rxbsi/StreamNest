package de.rxbsi.streamnest.storage.video.mapping;

import de.rxbsi.streamnest.storage.video.service.dto.Video;
import de.rxbsi.streamnest.storage.video.service.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "genres", source = "genres")
    VideoEntity toEntity(Video dto);

    @Mapping(target = "genres", source = "genres")
    Video toDto(VideoEntity entity);

}
