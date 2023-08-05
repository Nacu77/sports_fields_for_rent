package com.nacu.sport.api.mapper;

public abstract class AbstractBaseMapper<DTO, DOC> implements BaseMapper<DTO, DOC>
{
    @Override
    public abstract DTO entityToDto(DOC doc);

    @Override
    public abstract DOC dtoToEntity(DTO dto);
}
