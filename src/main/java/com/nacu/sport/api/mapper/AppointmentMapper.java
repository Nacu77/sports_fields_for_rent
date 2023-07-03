package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper extends BaseMapper<AppointmentDTO, Appointment>
{
}
