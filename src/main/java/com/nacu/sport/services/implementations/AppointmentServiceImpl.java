package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.mapper.AppointmentMapper;
import com.nacu.sport.model.Appointment;
import com.nacu.sport.repositories.AppointmentRepository;
import com.nacu.sport.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends CrudServiceImpl<AppointmentDTO, Appointment> implements AppointmentService
{
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper)
    {
        super(repository, mapper);
    }
}
