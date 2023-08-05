package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.model.SportField;
import com.nacu.sport.services.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = { ScheduleMapper.class, AddressMapper.class })
public abstract class SportFieldMapper extends AbstractBaseMapper<SportFieldDTO, SportField>
{
    @Autowired
    protected ImageService imageService;

    @Override
    @Mapping(target = "primaryImage", expression = "java(" +
            "sportField.getPrimaryImageName() != null" +
            "? imageService.getBySportFieldIdAndImageName(sportField.getId(), sportField.getPrimaryImageName())" +
            ": null" +
            ")")
    public abstract SportFieldDTO entityToDto(SportField sportField);
}
