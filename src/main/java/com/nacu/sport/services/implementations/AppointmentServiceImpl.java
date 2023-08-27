package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.mapper.AppointmentMapper;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificDateRequest;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificFieldRequest;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificUserRequest;
import com.nacu.sport.model.Appointment;
import com.nacu.sport.repositories.AppointmentRepository;
import com.nacu.sport.services.AppointmentPostService;
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

    private final AppointmentPostService appointmentPostService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper, AppointmentPostService appointmentPostService)
    {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.appointmentPostService = appointmentPostService;
    }

    @Override
    public void deleteById(String id)
    {
        super.deleteById(id);
        appointmentPostService.deleteByAppointmentId(id);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsForSpecificDate(GetAppointmentsForSpecificDateRequest request)
    {
        LocalDateTime startDate = request.getDate().atStartOfDay();
        LocalDateTime endDate = request.getDate().atTime(LocalTime.MAX);
        String sportFieldId = request.getSportFieldId();

        return repository.findAllBySportFieldIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualOrderByStartDateTimeAsc(sportFieldId, startDate, endDate)
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

    @Override
    public List<AppointmentDTO> getAppointmentsForSpecificField(GetAppointmentsForSpecificFieldRequest request)
    {
        List<Appointment> appointments;
        if (request.isCurrent())
        {
            appointments = repository.findAllBySportFieldIdAndEndDateTimeGreaterThan(request.getSportFieldId(), LocalDateTime.now());
        }
        else
        {
            appointments = repository.findAllBySportFieldIdAndEndDateTimeLessThan(request.getSportFieldId(), LocalDateTime.now());
        }

        return appointments
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCurrentAppointmentsForSpecificField(String sportFieldId)
    {
        repository.deleteAllBySportFieldIdAndEndDateTimeGreaterThan(sportFieldId, LocalDateTime.now());
    }
}
