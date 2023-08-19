package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.mapper.AppointmentMapper;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificDateRequest;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificUserRequest;
import com.nacu.sport.model.Appointment;
import com.nacu.sport.repositories.AppointmentRepository;
import com.nacu.sport.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl extends CrudServiceImpl<AppointmentDTO, Appointment> implements AppointmentService
{
    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper)
    {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsForSpecificDate(GetAppointmentsForSpecificDateRequest request)
    {
        LocalDateTime startDate = request.getDate().atStartOfDay();
        LocalDateTime endDate = request.getDate().atTime(LocalTime.MAX);
        String sportFieldId = request.getSportFieldId();

        return repository.findAllBySportFieldIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(sportFieldId, startDate, endDate)
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsForSpecificUser(GetAppointmentsForSpecificUserRequest request)
    {
        List<Appointment> appointments;
        if (request.isCurrent())
        {
            appointments = repository.findAllByCreatedByAndEndDateTimeGreaterThan(request.getUsername(), LocalDateTime.now());
        }
        else
        {
            appointments = repository.findAllByCreatedByAndEndDateTimeLessThan(request.getUsername(), LocalDateTime.now());
        }

        return appointments
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }
}
