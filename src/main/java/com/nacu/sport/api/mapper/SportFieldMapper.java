package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.model.SportField;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = { ScheduleMapper.class, AddressMapper.class })
public interface SportFieldMapper extends BaseMapper<SportFieldDTO, SportField>
{
}
