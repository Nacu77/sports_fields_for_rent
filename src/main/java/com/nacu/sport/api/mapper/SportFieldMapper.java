package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.model.SportField;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface SportFieldMapper
{
    SportFieldDTO entityToDto(SportField sportField);
    SportField dtoToEntity(SportFieldDTO sportFieldDTO);
}
