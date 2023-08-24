package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.model.AppointmentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { AppointmentMapper.class })
public interface AppointmentPostMapper extends BaseMapper<AppointmentPostDTO, AppointmentPost>
{
}
