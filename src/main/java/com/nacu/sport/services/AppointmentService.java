package com.nacu.sport.services;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificDateRequest;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificFieldRequest;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificUserRequest;

import java.util.List;

public interface AppointmentService extends CrudService<AppointmentDTO, String>
{
    List<AppointmentDTO> getAppointmentsForSpecificDate(GetAppointmentsForSpecificDateRequest request);
    List<AppointmentDTO> getAppointmentsForSpecificUser(GetAppointmentsForSpecificUserRequest request);
    List<AppointmentDTO> getAppointmentsForSpecificField(GetAppointmentsForSpecificFieldRequest request);
}
