package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.api.mapper.AppointmentPostMapper;
import com.nacu.sport.api.requests.GetFilteredPostsRequest;
import com.nacu.sport.exceptions.AppointmentPostAlreadyExistsException;
import com.nacu.sport.model.AppointmentPost;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.AppointmentPostRepository;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.AppointmentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentPostServiceImpl extends CrudServiceImpl<AppointmentPostDTO, AppointmentPost> implements AppointmentPostService
{
    private final AppointmentPostRepository repository;
    private final AppointmentPostMapper mapper;
    private final SportFieldRepository sportFieldRepository;

    @Autowired
    public AppointmentPostServiceImpl(AppointmentPostRepository repository, AppointmentPostMapper mapper, SportFieldRepository sportFieldRepository)
    {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.sportFieldRepository =sportFieldRepository;
    }

    @Override
    public AppointmentPostDTO create(AppointmentPostDTO appointmentPostDTO)
    {
        if (repository.findByAppointmentId(appointmentPostDTO.getAppointment().getId()).isPresent()) {
            throw new AppointmentPostAlreadyExistsException();
        }
        return super.create(appointmentPostDTO);
    }

    @Override
    public List<AppointmentPostDTO> getAllAppointmentPostsWithFreeSlots()
    {
        return StreamSupport.stream(repository.findAll(Sort.by(Sort.Order.asc("appointment.startDateTime"))).spliterator(), true)
                .filter(appointmentPost -> {
                    if (appointmentPost.getApplicants() == null)
                    {
                        return true;
                    }
                    return appointmentPost.getSlots() > appointmentPost.getApplicants().size();
                })
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentPostDTO> getFilteredAppointmentPostsWithFreeSlots(GetFilteredPostsRequest getFilteredPostsRequest)
    {
        List<SportField> sportFields =
                sportFieldRepository.findAllFiltered(getFilteredPostsRequest.getMinPrice() != null ? getFilteredPostsRequest.getMinPrice() : 0d,
                                                     getFilteredPostsRequest.getMaxPrice() != null ? getFilteredPostsRequest.getMaxPrice() : 100000000d,
                                                     getFilteredPostsRequest.getCountry() != null ? getFilteredPostsRequest.getCountry() : "*",
                                                     getFilteredPostsRequest.getCity() != null ? getFilteredPostsRequest.getCity() : "*",
                                                     getFilteredPostsRequest.getName() != null ? getFilteredPostsRequest.getName(): "*",
                                                     getFilteredPostsRequest.getType() != null ? getFilteredPostsRequest.getType(): "*");

        List<AppointmentPost> appointmentPosts =
                sportFields.stream()
                        .map(SportField::getId)
                        .map(repository::findAllBySportFieldId)
                        .flatMap(Collection::stream)
                        .sorted(Comparator.comparing(post -> post.getAppointment().getStartDateTime()))
                        .toList();

        return appointmentPosts.parallelStream()
                .filter(appointmentPost -> {
                    if (appointmentPost.getApplicants() == null)
                    {
                        return true;
                    }
                    return appointmentPost.getSlots() > appointmentPost.getApplicants().size();
                })
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentPostDTO> getAppointmentPostsForSpecificUser(String username)
    {
        return repository.findAllByUsername(username, Sort.by(Sort.Order.asc("appointment.startDateTime")))
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentPostDTO> getAppliedAppointmentPostsForSpecificUser(String username)
    {
        return repository.findAllByUsernameInApplicants(username, Sort.by(Sort.Order.asc("appointment.startDateTime")))
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByAppointmentId(String appointmentId)
    {
        repository.findByAppointmentId(appointmentId).ifPresent(repository::delete);
    }
}
