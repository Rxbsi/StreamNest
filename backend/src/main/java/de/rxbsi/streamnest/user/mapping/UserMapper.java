package de.rxbsi.streamnest.user.mapping;

import de.rxbsi.streamnest.user.service.User;
import org.mapstruct.Mapper;
import de.rxbsi.streamnest.user.service.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User dto);
    User toDto(UserEntity entity);

}
