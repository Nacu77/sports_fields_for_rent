package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.api.mapper.AppointmentPostMapper;
import com.nacu.sport.model.AppointmentPost;
import com.nacu.sport.repositories.AppointmentPostRepository;
import com.nacu.sport.services.AppointmentPostService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentPostServiceImpl extends CrudServiceImpl<AppointmentPostDTO, AppointmentPost> implements AppointmentPostService
{
    public AppointmentPostServiceImpl(AppointmentPostRepository repository, AppointmentPostMapper mapper)
    {
        super(repository, mapper);
    }
}
