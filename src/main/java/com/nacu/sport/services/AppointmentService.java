package com.nacu.sport.services;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificDateRequest;

import java.util.List;

public interface AppointmentService extends CrudService<AppointmentDTO, String>
{
    List<AppointmentDTO> getAppointmentsForSpecificDate(GetAppointmentsForSpecificDateRequest request);
}
