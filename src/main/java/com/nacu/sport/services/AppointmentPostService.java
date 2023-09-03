package com.nacu.sport.services;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.api.requests.GetFilteredPostsRequest;

import java.util.List;

public interface AppointmentPostService extends CrudService<AppointmentPostDTO, String>
{
    List<AppointmentPostDTO> getAllAppointmentPostsWithFreeSlots();
    List<AppointmentPostDTO> getFilteredAppointmentPostsWithFreeSlots(GetFilteredPostsRequest getFilteredPostsRequest);
    List<AppointmentPostDTO> getAppointmentPostsForSpecificUser(String username);
    List<AppointmentPostDTO> getAppliedAppointmentPostsForSpecificUser(String username);
    void deleteByAppointmentId(String appointmentId);
}
