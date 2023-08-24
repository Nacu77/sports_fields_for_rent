package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.api.mapper.AppointmentPostMapper;
import com.nacu.sport.model.AppointmentPost;
import com.nacu.sport.repositories.AppointmentPostRepository;
import com.nacu.sport.services.AppointmentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentPostServiceImpl extends CrudServiceImpl<AppointmentPostDTO, AppointmentPost> implements AppointmentPostService
{
    private final AppointmentPostRepository repository;
    private final AppointmentPostMapper mapper;

    @Autowired
    public AppointmentPostServiceImpl(AppointmentPostRepository repository, AppointmentPostMapper mapper)
    {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AppointmentPostDTO> getAppointmentPostsForSpecificUser(String username)
    {
        return repository.findAllByUsername(username)
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }
}
