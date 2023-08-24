package com.nacu.sport.services;

import com.nacu.sport.api.dtos.AppointmentPostDTO;

import java.util.List;

public interface AppointmentPostService extends CrudService<AppointmentPostDTO, String>
{
    List<AppointmentPostDTO> getAppointmentPostsForSpecificUser(String username);
}
