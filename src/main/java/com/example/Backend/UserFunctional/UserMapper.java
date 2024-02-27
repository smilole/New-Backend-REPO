package com.example.Backend.UserFunctional;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserEntity toEntity(UserDTO userDTO);
    UserDTO toDTO(UserEntity userEntity);
}
