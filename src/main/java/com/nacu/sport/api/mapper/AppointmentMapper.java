package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.model.Appointment;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", imports = SportField.class)
public abstract class AppointmentMapper extends AbstractBaseMapper<AppointmentDTO, Appointment>
{
    @Autowired
    protected SportFieldRepository sportFieldRepository;

    @Override
    @Mapping(target = "sportFieldName", expression = "java(" +
            "sportFieldRepository.findById(appointment.getSportFieldId())" +
            ".orElse(SportField.builder().name(\"Deleted Field\").build())" +
            ".getName()" +
            ")")
    public abstract AppointmentDTO entityToDto(Appointment appointment);
}
