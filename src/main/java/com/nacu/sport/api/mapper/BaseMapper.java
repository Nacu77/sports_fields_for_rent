package com.nacu.sport.api.mapper;

public interface BaseMapper<DTO, DOC>
{
    DTO entityToDto(DOC doc);
    DOC dtoToEntity(DTO dto);
}
