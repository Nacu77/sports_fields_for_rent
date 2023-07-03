package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.ScheduleDTO;
import com.nacu.sport.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper extends BaseMapper<ScheduleDTO, Schedule>
{
}
