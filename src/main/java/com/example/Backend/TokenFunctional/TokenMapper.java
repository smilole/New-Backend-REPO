package com.example.Backend.TokenFunctional;

import org.mapstruct.Mapper;

@Mapper
public interface TokenMapper {
    TokenDTO toDTO(TokenEntity tokenEntity);
    TokenEntity toEntity(TokenDTO tokenDTO);
}
